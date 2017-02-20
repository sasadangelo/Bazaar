package nbp.jspcart.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import nbp.jspcart.WebSite;
import nbp.jspcart.data.DBConnection;

import org.opencommunity.bazaar.utility.DBUtility;

//import nbp.jspcart.referral.*;

public class Users {

	WebSite website = null;

	public Users(WebSite ws) {
		website = ws;
	}

	public String insert(UserProfile obj) {
		if (get(obj.txtEmailAddress) != null) {
			return "L'indirizzo email '"
					+ obj.txtEmailAddress
					+ "' gia' esiste nel database, utilizza un indirizzo diverso o richiedi la password se l'hai dimenticata.";
		}

		if (obj.txtReferredBy != null && obj.txtReferredBy.length() > 1) {
			UserProfile up = getProfile(obj.txtReferredBy);
			if (up == null)
				return "Il cliente con indirizzo email '"
						+ obj.txtReferredBy
						+ "' non esiste nel database, verifica i dati inseriti.";
		}

		DBConnection conn = website.getConnection();
		synchronized (conn) {
			if (!obj.insert(conn))
				return "Si e' verificato un errore nel database";
		}

		website.mailer.sendSignupMail(obj);

		return null;
	}

	public String edit(UserProfile obj) {
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			return obj.edit(conn) ? null
					: "Si e' verificato un errore nel database";
		}
	}

	public String delete(UserProfile obj) {
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			return obj.delete(conn) ? null
					: "Si e' verificato un errore nel database";
		}
	}

	public Vector getAll(int nStart, int nSize) {
		Vector items = new Vector();
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			try {
				PreparedStatement stmt = conn
						.prepareStatement("SELECT * FROM Users LIMIT ?,?");
				stmt.setInt(1, nStart);
				stmt.setInt(2, nSize);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					items.addElement(new UserProfile(rs));
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				website.logger.write(e);
			}
		}
		return items;
	}

	public Vector getAll(String ch) {
		Vector items = new Vector();
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			try {

				PreparedStatement stmt = conn
						.prepareStatement("SELECT * FROM Users WHERE txtEmailAddress LIKE ? ORDER BY tsRegTime DESC");
				stmt.setString(1, ch + "%");
				//stmt.setInt(1,nStart);
				//stmt.setInt(2,nSize);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					items.addElement(new UserProfile(rs));
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				website.logger.write(e);
			}
		}
		return items;
	}

	public Vector getAllByLatest() {
		Vector items = new Vector();
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			try {

				PreparedStatement stmt = conn
						.prepareStatement("SELECT * FROM Users ORDER BY tsRegTime DESC");
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					items.addElement(new UserProfile(rs));
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				website.logger.write(e);
			}
		}
		return items;
	}

	public boolean exists(String txtEmailAddress) {
		boolean bExists = false;
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			try {
				PreparedStatement stmt = conn
						.prepareStatement("SELECT txtEmailAddress FROM Users WHERE txtEmailAddress=?");
				stmt.setString(1, txtEmailAddress);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					bExists = true;
				}
				rs.close();
				stmt.close();

			} catch (Exception e) {
				website.logger.write(e);
			}
		}
		return bExists;
	}

	public Vector getReferredFriends(String txtEmailAddress) {
		Vector items = new Vector();
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			try {
				PreparedStatement stmt = conn
						.prepareStatement("SELECT * FROM Users WHERE txtReferredBy=?");
				stmt.setString(1, txtEmailAddress);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					User user = new User(rs);
					items.addElement(user);
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				website.logger.write(e);
			}
		}
		return items;
	}

	public UserProfile getProfile(String txtEmailAddress) {
		DBConnection conn = website.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		synchronized (conn) {
			try {
				stmt = conn.prepareStatement("SELECT * FROM Users WHERE txtEmailAddress=?");
				stmt.setString(1, txtEmailAddress);
				rs = stmt.executeQuery();
				if (rs.next()) {
					UserProfile user = new UserProfile(rs);
					return user;
				}
			} catch (Exception e) {
				website.logger.write(e);
			} finally {
				DBUtility.close(rs);
				DBUtility.close(stmt);
			}
		}
		return null;
	}

	public User get(String txtEmailAddress) {
		DBConnection conn = website.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		synchronized (conn) {
			try {
				stmt = conn.prepareStatement("SELECT txtEmailAddress,txtFirstname,txtLastname,txtPassword,nACLBitmap,bSendPromotion,bNeverSendMail FROM Users WHERE txtEmailAddress=?");
				stmt.setString(1, txtEmailAddress);
				rs = stmt.executeQuery();
				if (rs.next()) {
					User user = new User(rs);
					return user;
				}
			} catch (Exception e) {
				website.logger.write(e);
			} finally {
				DBUtility.close(rs);
				DBUtility.close(stmt);
			}
		}
		return null;
	}

	public UserProfile getAdmin() {
		DBConnection conn = website.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		synchronized (conn) {
			try {
				stmt = conn.prepareStatement("SELECT * FROM Users WHERE nACLBitmap & " + User.ADMIN_BIT_MAP + " > 0");
				rs = stmt.executeQuery();
				if (rs.next()) {
					UserProfile user = new UserProfile(rs);
					return user;
				}
			} catch (Exception e) {
				website.logger.write(e);
			} finally {
				DBUtility.close(rs);
				DBUtility.close(stmt);
			}
		}
		return null;
	}
	
	public String sendPassword(User user) {/*
		nbp.jspcart.email.Email forgotMail = new nbp.jspcart.email.Email(
				website, "ForgotPassword");
		forgotMail.replace("$USER$", user.txtName);
		forgotMail.replace("$PASSWORD$", user.txtPassword);
		forgotMail.replace("$SHOPNAME$", website.shopName);
		forgotMail.replace("$SHOPCOMPANY$", website.shopCompany);
		forgotMail.setFrom(website.shopName, website.shopSupportEmail);
		forgotMail.to = user.txtUsername;
		return forgotMail.send();*/
		return null;
	}

	public String changePassword(String email, String oldPassword, String newPassword) {
		User user = get(email);
		if (!user.txtPassword.equals(oldPassword))
			return "La vecchia password non e' corretta !!";
		DBConnection conn = website.getConnection();
		PreparedStatement stmt = null;
		synchronized (conn) {
			try {
				stmt = conn
						.prepareStatement("UPDATE Users SET txtPassword=? WHERE txtEmailAddress=?");
				stmt.setString(1, newPassword);
				stmt.setString(2, email);
				stmt.execute();
			} catch (Exception e) {
				website.logger.write(e);
				return "Si e' verificato un errore nel database : "
						+ e.getMessage();
			} finally {
				DBUtility.close(stmt);
			}
		}
		return null;
	}
}
