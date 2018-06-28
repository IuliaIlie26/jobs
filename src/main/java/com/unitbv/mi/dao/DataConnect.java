package com.unitbv.mi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.unitbv.mi.exceptions.CustomException;
import com.unitbv.mi.utils.ApplicantUtils;
import com.unitbv.mi.utils.JobResultsUtils;
import com.unitbv.mi.utils.SearchResultsUtils;
import com.unitbv.mi.utils.UUIDGeneratorUtils;

public class DataConnect {

	private final static Logger logger = Logger.getLogger(DataConnect.class);
	private static Connection con;

	public static List<JobResultsUtils> getAll(String company) {

		List<JobResultsUtils> results = new ArrayList<>();

		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("select id, position, city, recruiter from jobs where company = ?");
					ps.setString(1, company);
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						JobResultsUtils job = new JobResultsUtils(rs.getString("id"), rs.getString("position"),
								rs.getString("city"), rs.getString("recruiter"));
						results.add(job);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return results;
	}

	public static int validate(String user, String password) {

		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {

					ps = con.prepareStatement(
							"Select username, password from Users where username = ? and password = ?");
					ps.setString(1, user);
					ps.setString(2, password);

					ResultSet rs = ps.executeQuery();

					if (rs.next()) {
						return 1;
					}

					ps = con.prepareStatement("Select email, password from Users where email = ? and password = ?");
					ps.setString(1, user);
					ps.setString(2, password);

					rs = ps.executeQuery();

					if (rs.next()) {
						return 1;
					}

					ps = con.prepareStatement(
							"Select username, password from recruiter where username = ? and password = ?");
					ps.setString(1, user);
					ps.setString(2, password);

					rs = ps.executeQuery();

					if (rs.next()) {
						return 2;
					}

					ps = con.prepareStatement("Select email, password from recruiter where email = ? and password = ?");
					ps.setString(1, user);
					ps.setString(2, password);

					rs = ps.executeQuery();

					if (rs.next()) {
						return 2;
					}
				} catch (SQLException ex) {
					System.out.println("Login error -->" + ex.getMessage());
					ex.printStackTrace();
				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static String getNameAndLastname(String username) {

		PreparedStatement ps = null;
		String nameAndLastname = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement(
							"Select lastname, name from Users where username = ? or email = ? or idusers =?");
					ps.setString(1, username);
					ps.setString(2, username);
					ps.setString(3, username);

					ResultSet rs = ps.executeQuery();

					if (rs.next()) {
						nameAndLastname = rs.getString("lastname") + " " + rs.getString("name");
					}

					ps = con.prepareStatement("Select lastname, name from recruiter where username = ? or email = ?");
					ps.setString(1, username);
					ps.setString(2, username);

					rs = ps.executeQuery();

					if (rs.next()) {
						nameAndLastname = rs.getString("lastname") + " " + rs.getString("name");
					}
				} catch (SQLException ex) {
					ex.printStackTrace();

				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return nameAndLastname;
	}

	public static String getIdByUsername(String username) {
		String id = null;
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("Select idusers from Users where username = ? or email = ?");
					ps.setString(1, username);
					ps.setString(2, username);
					ResultSet rs = ps.executeQuery();

					if (rs.next()) {
						id = rs.getString("idusers");
					}
				} catch (SQLException ex) {
					ex.printStackTrace();

				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return id;

	}

	public static boolean validateRegistration(String name, String lastname, String username, String password,
			String phone, String email) {
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("insert into users values (?,?,?,?,?,?,?)");
					ps.setString(1, UUIDGeneratorUtils.generate());
					ps.setString(2, name);
					ps.setString(3, lastname);
					ps.setString(4, username);
					ps.setString(5, password);
					ps.setString(7, phone);
					ps.setString(6, email);
					ps.executeUpdate();
				} catch (SQLException ex) {
					ex.printStackTrace();
					return false;

				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean update(String value, String username, String column) {
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("update users set " + column + " = ? where username = ?");
					ps.setString(1, value);
					ps.setString(2, username);
					ps.executeUpdate();
				} catch (SQLException ex) {
					ex.printStackTrace();
					return false;

				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return true;

	}

	public static String selectFromUsers(String value, String username) {
		PreparedStatement ps = null;
		String result = "";
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("select " + value + " from users where username = ?");
					ps.setString(1, username);
					ps.executeQuery();
					ResultSet rs = ps.executeQuery();

					if (rs.next()) {
						result = rs.getString(value);
					}

				} catch (SQLException ex) {
					ex.printStackTrace();

				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean name(String name, String lastname, String username, String password, String email,
			String company) {

		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("insert into recruiter values (?,?,?,?,?,?,?)");
					ps.setString(1, UUIDGeneratorUtils.generate());
					ps.setString(2, name);
					ps.setString(3, lastname);
					ps.setString(6, username);
					ps.setString(7, password);
					ps.setString(5, company);
					ps.setString(4, email);
					ps.executeUpdate();
				} catch (SQLException ex) {
					ex.printStackTrace();
					return false;

				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static String getCity(String user) {
		PreparedStatement ps = null;
		String city = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement(
							"select city from jobs join applications on jobs.id = applications.position where applications.applicant = ?");
					ps.setString(1, user);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						city = rs.getString("city");
					}
				} catch (SQLException ex) {

					ex.printStackTrace();

				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return city;
	}

	public static Connection getConnection() throws CustomException {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost/postgres?user=postgres&password=iulia");
			logger.info("Connected to Postgres!");

		} catch (Exception ex) {
			logger.error("Database.getConnection() Error --> ");
			ex.printStackTrace();
			throw new CustomException();

		}
		return con;
	}

	public static void close(Connection con) {
		try {
			if (con != null) {
				con.close();
				logger.info("Connection to Postgres closed!");
			}
		} catch (Exception ex) {
			logger.error("Error closing Postgres connection: " + ex.getMessage());
		}
	}

	public static List<SelectItem> getCities() {
		List<SelectItem> results = new ArrayList<>();
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("select distinct city from jobs order by city");
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						results.add(new SelectItem(rs.getString("city")));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DataConnect.close(con);
				}
			}

		} catch (CustomException e) {
			e.printStackTrace();
		}
		return results;

	}

	public static List<SearchResultsUtils> getResults(String domain, String city, String company, String position) {

		List<SearchResultsUtils> results = new ArrayList<>();
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					String sql = "select id, position, company, website, city, description from jobs where domain= ? and city= ? ";
					if (company != null && (!company.equals(""))) {
						if (position != null && (!position.equals(""))) {
							sql += " and company like ?  and position like ?";
							ps = con.prepareStatement(sql);
							ps.setString(3, "%" + company + "%");
							ps.setString(4, "%" + position + "%");
						} else {
							sql += " and company like ?";
							ps = con.prepareStatement(sql);
							ps.setString(3, "%" + company + "%");
						}
					} else if (position != null && (!position.equals(""))) {
						sql += " and position like ?";
						ps = con.prepareStatement(sql);
						ps.setString(3, "%" + position + "%");
					} else {
						ps = con.prepareStatement(sql);
					}
					ps.setString(1, domain);
					ps.setString(2, city);

					ResultSet rs = ps.executeQuery();
					while (rs.next()) {

						SearchResultsUtils row = new SearchResultsUtils(rs.getString("id"), rs.getString("position"),
								rs.getString("company"), rs.getString("city"), rs.getString("description"),
								rs.getString("website"));
						results.add(row);
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return results;
	}

	public static List<SelectItem> selectDomains() {
		List<SelectItem> list = new ArrayList<>();
		String sql = "SELECT distinct DOMAIN FROM JOBS ORDER BY DOMAIN	";
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						list.add(new SelectItem(rs.getString("domain")));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static boolean publish(String position, String domain, String company, String description, String city,
			String recruiter, String website) {
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("insert into jobs values (?,?,?,?,?,?,?,?)");
					ps.setString(1, UUIDGeneratorUtils.generate());
					ps.setString(2, position);
					ps.setString(8, company);
					ps.setString(3, city);
					ps.setString(6, website);
					ps.setString(5, description);
					ps.setString(4, domain);
					ps.setString(7, recruiter);
					ps.executeUpdate();

				} catch (SQLException ex) {

					ex.printStackTrace();
					return false;

				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static String selectFromJobs(String column, String id) {
		String result = null;
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("select " + column + " from jobs where id = ?");
					ps.setString(1, id);
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						result = rs.getString(column);
					}

				} catch (SQLException ex) {

					ex.printStackTrace();

				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getPositionById(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		String position = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("select position from jobs where id = ?");
					ps.setString(1, id);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						position = rs.getString("position");
					}
				} catch (SQLException ex) {

					ex.printStackTrace();

				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return position;
	}

	public static boolean update(String id, String position, String domain, String description, String city,
			String website) {
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement(
							"update jobs set position = ? , city = ?, website = ?, description =?, domain =? where id = ?");

					ps.setString(1, position);
					ps.setString(2, city);
					ps.setString(3, website);
					ps.setString(4, description);
					ps.setString(5, domain);
					ps.setString(6, id);
					ps.executeUpdate();

				} catch (SQLException ex) {

					ex.printStackTrace();
					return false;

				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return true;

	}

	public static String getCityById(String id) {
		PreparedStatement ps = null;
		String city = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("select city from jobs where id = ?");
					ps.setString(1, id);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						city = rs.getString("city");
					}
				} catch (SQLException ex) {

					ex.printStackTrace();

				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return city;
	}

	public static String getCompany(String id) {
		PreparedStatement ps = null;
		String company = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("select company from jobs where id = ?");
					ps.setString(1, id);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						company = rs.getString("company");
					}
				} catch (SQLException ex) {

					ex.printStackTrace();

				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return company;
	}

	public static List<SelectItem> getCitiesByCompany(String company) {

		PreparedStatement ps = null;
		List<SelectItem> cities = new ArrayList<>();
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("select city from jobs where company = ?");
					ps.setString(1, company);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						cities.add(new SelectItem(rs.getString("city")));
					}
				} catch (SQLException ex) {

					ex.printStackTrace();

				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}

		return cities;
	}

	public static List<SelectItem> getPositions(String company) {

		PreparedStatement ps = null;
		List<SelectItem> positions = new ArrayList<>();
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("select position from jobs where company = ?");
					ps.setString(1, company);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						positions.add(new SelectItem(rs.getString("position")));
					}
				} catch (SQLException ex) {

					ex.printStackTrace();

				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return positions;
	}

	public static String getIDByPosition(String selectedPosition, String company, String selectedCity) {

		PreparedStatement ps = null;
		String position = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("select id from jobs where company = ? and city = ? and position = ?");
					ps.setString(1, company);
					ps.setString(2, selectedCity);
					ps.setString(3, selectedPosition);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						position = rs.getString("id");
					}
				} catch (SQLException ex) {

					ex.printStackTrace();

				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return position;
	}

	public static String getCompanyForRecruiter(String recruiter) {
		String company = null;
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("Select company from recruiter where username = ? or email = ?");
					ps.setString(1, recruiter);
					ps.setString(2, recruiter);
					ResultSet rs = ps.executeQuery();

					if (rs.next()) {
						company = rs.getString("company");
					}
				} catch (SQLException ex) {
					ex.printStackTrace();

				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return company;
	}

	public static boolean sendApplication(String position, String username) {

		String userID = UsersDAO.getIdByUsername(username);

		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("insert into applications values(?,?,?,?)");
					String uuid = UUIDGeneratorUtils.generate();
					ps.setString(1, uuid);
					ps.setString(2, userID);
					ps.setString(3, position);
					String company = JobsDAO.getCompany(position);
					ps.setString(4, company);
					ps.executeUpdate();

				} catch (SQLException ex) {
					ex.printStackTrace();
					return false;
				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean hasCV(String username) {
		username = UsersDAO.getIdByUsername(username);
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("select username from cv where username = ?");
					ps.setString(1, username);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						return true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return false;

	}

	public static boolean sendCV(String usernameID, String languages, String selectedDomain, String skills,
			double experience, String city) {
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("insert into cv values(?,?,?,?,?,?,?)");
					String uuid = UUIDGeneratorUtils.generate();
					ps.setString(1, uuid);
					ps.setString(2, usernameID);
					ps.setString(3, skills);
					ps.setDouble(4, experience);
					ps.setString(5, languages);
					ps.setString(6, selectedDomain);
					ps.setString(7, city);
					ps.executeUpdate();

				} catch (SQLException ex) {
					ex.printStackTrace();
					return false;
				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static List<ApplicantUtils> searchApplicants(String selectedDomain, String city) {
		List<ApplicantUtils> list = new ArrayList<>();
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement(
							"select id, username, city, experience, skills, languages from cv where domain = ? and city = ?");
					ps.setString(1, selectedDomain);
					ps.setString(2, city);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						list.add(new ApplicantUtils(rs.getString("id"),
								UsersDAO.getNameAndLastname(rs.getString("username")), rs.getString("city"),
								rs.getString("experience"), rs.getString("skills"), rs.getString("languages")));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<ApplicantUtils> getApplication(String selectedPosition, String selectedCity, String company) {
		List<ApplicantUtils> list = new ArrayList<>();

		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement(
							"select cv.id, username, city, experience, skills, languages, domain from cv join applications on username = applicant where position = ?");
					ps.setString(1, JobsDAO.getIDByPosition(selectedPosition, company, selectedCity));
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						list.add(new ApplicantUtils(rs.getString("id"),
								UsersDAO.getNameAndLastname(rs.getString("username")), rs.getString("city"),
								rs.getString("experience"), rs.getString("skills"), rs.getString("languages")));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}

		return list;

	}

	public static synchronized String getApplicant(String id) {
		String username = null;

		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("select username from cv where id = ?");
					ps.setString(1, id);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						username = rs.getString("username");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return username;

	}

	public static boolean hasApplied(String username, String position) {

		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement(
							"select applicant from applications where applicant = ? and position = ?");
					ps.setString(1, UsersDAO.getIdByUsername(username));
					ps.setString(2, position);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						return true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean deleteCV(String username) {
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("delete from cv where username = ?");
					ps.setString(1, username);
					ps.executeUpdate();
				} catch (SQLException ex) {
					ex.printStackTrace();
					return false;
				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean updateCV(String usernameID, String languages, String selectedDomain, String skills,
			double exp, String city) {
		DataConnect.deleteCV(usernameID);
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {

					ps = con.prepareStatement("insert into cv values(?,?,?,?,?,?,?)");
					String uuid = UUIDGeneratorUtils.generate();
					ps.setString(1, uuid);
					ps.setString(2, usernameID);
					ps.setString(3, skills);
					ps.setDouble(4, exp);
					ps.setString(5, languages);
					ps.setString(6, selectedDomain);
					ps.setString(7, city);
					ps.executeUpdate();

				} catch (SQLException ex) {
					ex.printStackTrace();
					return false;
				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static String selectFromCV(String usernameID, String column) {
		String result = null;
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			synchronized (con) {
				try {
					ps = con.prepareStatement("select " + column + " from cv where username = ?");
					ps.setString(1, usernameID);
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						result = rs.getString(column);
					}

				} catch (SQLException ex) {

					ex.printStackTrace();

				} finally {
					DataConnect.close(con);
				}
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return result;
	}
}
