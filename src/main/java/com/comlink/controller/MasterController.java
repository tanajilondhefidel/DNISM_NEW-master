package com.comlink.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.comlink.dao.ConfigDao;
import com.comlink.dao.DnisDao;
import com.comlink.dao.LogDao;
import com.comlink.dao.UserDao;
import com.comlink.model.Config;
import com.comlink.model.DNIS;
import com.comlink.model.DNISTarget;
import com.comlink.model.GATEWAYS;
import com.comlink.model.GATEWAYSHost;
import com.comlink.model.Log;
import com.comlink.model.User;

@Controller
@RequestMapping("/")
public class MasterController extends MultiActionController {

	@Autowired
	private UserDao userDAO;

	@Autowired
	private LogDao logDAO;

	@Autowired
	private DnisDao dnisDAO;

	@Autowired
	private ConfigDao configDao;

	public UserDao getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDao userDAO) {
		this.userDAO = userDAO;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap model) {
		return "index";
	}

	@Value("${upload.file.path}")
	private String path;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelview = new ModelAndView();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			modelview.setViewName("dashboard");
			modelview.addObject("sessionuser", user);
			return modelview;
		} else {
			modelview.setViewName("index");
			modelview.addObject("message", " Insert data first.");
		}
		return modelview;
	}

	@RequestMapping(value = "/dnismanagement", method = RequestMethod.GET)
	public ModelAndView dnisShow(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelview = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null) {
			User sessionuser = (User) session.getAttribute("user");
			if (sessionuser != null) {

				List<String> gatways = dnisDAO.getGatewaysfromDb();
				modelview.addObject("sessionuser", sessionuser);
				modelview.addObject("gatways", gatways);
				modelview.setViewName("dnismanagement");
				return modelview;
			} else {
				modelview.setViewName("index");
				return modelview;
			}
		} else {
			modelview.setViewName("index");
			return modelview;
		}
	}

	@RequestMapping(value = "/dnismanagement", method = RequestMethod.POST)
	public ModelAndView dnis(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		ModelAndView modelview = new ModelAndView();
		if (session != null) {
			User sessionuser = (User) session.getAttribute("user");
			if (sessionuser != null) {

				String status = request.getParameter("Status");
				String datem = request.getParameter("datem");
				String prefix_mapp = request.getParameter("prefixmapp");
				String file = request.getParameter("file");
				String did = request.getParameter("did");
				String ticnumber = request.getParameter("ticnumber");
				String gatewayname = request.getParameter("gatewayname");
				String pageLength = request.getParameter("example_length");
				String setpagination = request.getParameter("setpagination");
				session.setAttribute("Pagination", setpagination);
				List<String> gatways = dnisDAO.getGatewaysfromDb();
				List<DNIS> dnisList = dnisDAO.getDnisByparameter(status,
						prefix_mapp, file, did, ticnumber, gatewayname, datem);

				modelview.addObject("status", status);
				modelview.addObject("datem", datem);
				modelview.addObject("prefixmapp", prefix_mapp);
				modelview.addObject("file", file);
				modelview.addObject("did", did);
				modelview.addObject("ticnumber", ticnumber);
				modelview.addObject("gatewayname", gatewayname);
				modelview.addObject("dnisList", dnisList);
				modelview.addObject("sessionuser", sessionuser);
				modelview.addObject("gatways", gatways);
				modelview.addObject("Pagination", pageLength);
				modelview.setViewName("dnismanagement");
				return modelview;
			} else {
				modelview.setViewName("index");
				return modelview;
			}
		} else {
			modelview.setViewName("index");
			return modelview;
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(password);
		ModelAndView modelview = new ModelAndView();
		User user = userDAO.login(username, password);

		if (user != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			modelview.setViewName("dashboard");
			modelview.addObject("sessionuser", user);
			logDAO.LogEvent(user.getFirstName(), user.getLastName(),
					user.getId(), "Login User " + user.getFirstName());
			modelview.addObject("message", "Login Successfully.");
		} else {
			modelview.setViewName("index");
			modelview.addObject("message", "Username or Password are wrong!");
		}
		return modelview;
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ModelAndView uploadFile(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ModelAndView modelview = new ModelAndView();
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		if (user == null) {
			modelview.setViewName("index");
			return modelview;
		} else {
			response.setContentType("application/vnd.ms-excel");
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartRequest.getFile("file");
			ServletOutputStream stream = null;
			int iModify = 0;
			int iDelete = 0;
			int iAdd = 0;
			int iUndelete = 0;
			String exportfilename = "";
			boolean process = true;
			if ((multipartFile != null)
					&& (multipartFile.getOriginalFilename() != null)
					&& (!multipartFile.getOriginalFilename().isEmpty())) {
				DNIS dnis = new DNIS();
				String s = multipartFile.getOriginalFilename();
				dnis.setFilename(multipartFile.getOriginalFilename());
				ServletContext context = request.getServletContext();
				File fn1 = new File(context.getRealPath("/") + "/" + s);
				String dirPat = fn1.getAbsolutePath().toString();
				String rootPath = System.getProperty("catalina.home");
				byte[] bytes = multipartFile.getBytes();
				File dir = new File(rootPath + File.separator + "temp");

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + s);
				Date date = new Date();
				// getTime() returns current time in milliseconds
				long time = date.getTime();
				exportfilename = time + "DNISUploadStatus.xlsx";
				BufferedOutputStream streamfile = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				streamfile.write(bytes);
				streamfile.close();
				int Data = 0;
				String filePath = dirPat.substring(0,
						dirPat.lastIndexOf(File.separator));
				try {
					String dtfs = null;
					String action = null;
					String didnum = null;
					String prefix = null;
					String mnumber = null;
					String cname = null;
					String gateway = null;
					String tic = null;
					String dbAction = "";
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition",
							"attachment; filename=" + exportfilename);
					int indexrow = 1;
					XSSFWorkbook logworkbook = new XSSFWorkbook();
					XSSFSheet logsheet = logworkbook.createSheet("Report");
					XSSFRow rowheader = logsheet.createRow(indexrow);
					rowheader.createCell(0).setCellValue("DNIS");
					rowheader.createCell(1).setCellValue("Task");

					ArrayList<Object> cellArrayLisstHolder = new ArrayList<Object>();
					String filename = dirPat;
					String extension = FilenameUtils.getExtension(s);

					File file = new File(filename);
					FileInputStream fn = new FileInputStream(serverFile);
					InputStream input = new BufferedInputStream(fn);
					int maxNumOfCells = 0;
					List<DNIS> addDNISLst = new ArrayList<DNIS>();
					if ("xls".equals(extension)) {
						POIFSFileSystem fs = new POIFSFileSystem(input);
						HSSFWorkbook wb = new HSSFWorkbook(fs);
						HSSFSheet sheet = wb.getSheetAt(0);
						Iterator rows = sheet.rowIterator();
						maxNumOfCells = sheet.getRow(0).getLastCellNum();
						while (rows.hasNext()) {
							HSSFRow row = (HSSFRow) rows.next();
							Iterator cells = row.cellIterator();
							ArrayList cellStoreArrayList = new ArrayList();
							for (int cellCounter = 0; cellCounter < maxNumOfCells; cellCounter++) { // Loop
																									// through
																									// cells
								HSSFCell cell;
								if (row.getCell(cellCounter) == null) {
									cell = row.createCell(cellCounter);
									cell.setCellValue("");
								} else {
									cell = row.getCell(cellCounter);
									if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
										cell.setCellValue(NumberToTextConverter
												.toText(cell
														.getNumericCellValue()));
									}
								}
								cellStoreArrayList.add(cell);
							}
							cellArrayLisstHolder.add(cellStoreArrayList);

						}
					} else if ("xlsx".equals(extension)) {

						XSSFWorkbook workBook = new XSSFWorkbook(input);
						XSSFSheet sheet = workBook.getSheetAt(0);
						Iterator rows = sheet.rowIterator();
						maxNumOfCells = sheet.getRow(0).getLastCellNum();
						while (rows.hasNext()) {
							XSSFRow row = (XSSFRow) rows.next();
							Iterator cells = row.cellIterator();
							ArrayList cellStoreArrayList = new ArrayList();

							for (int cellCounter = 0; cellCounter < maxNumOfCells; cellCounter++) { // Loop
																									// through
																									// cells

								XSSFCell cell;

								if (row.getCell(cellCounter) == null) {
									cell = row.createCell(cellCounter);
									cell.setCellValue("");
								} else {
									cell = row.getCell(cellCounter);
									if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
										cell.setCellValue(NumberToTextConverter
												.toText(cell
														.getNumericCellValue()));
									}

								}
								cellStoreArrayList.add(cell);
							}
							cellArrayLisstHolder.add(cellStoreArrayList);
						}
					}
					Row logrow = logsheet.createRow(0);

					logrow.createCell(0).setCellValue("DNIS");
					logrow.createCell(1).setCellValue("ACTION");
					logrow.createCell(2).setCellValue("STATUS");
					for (int i = 1; i < cellArrayLisstHolder.size(); i++) {

						ArrayList cellStoreArrayList = (ArrayList) cellArrayLisstHolder
								.get(i);
						dtfs = cellStoreArrayList.get(0).toString()
								.replaceAll("[^a-zA-Z0-9]+", "");
						action = cellStoreArrayList.get(1).toString();
						didnum = cellStoreArrayList.get(2).toString()
								.replaceAll("[^a-zA-Z0-9]+", "");
						prefix = cellStoreArrayList.get(3).toString()
								.replaceAll("[^a-zA-Z0-9]+", "");
						mnumber = cellStoreArrayList.get(4).toString()
								.replaceAll("[^a-zA-Z0-9]+", "");
						cname = cellStoreArrayList.get(5).toString();
						gateway = cellStoreArrayList.get(6).toString();
						tic = cellStoreArrayList.get(7).toString()
								.replaceAll("[^a-zA-Z0-9]+", "");
						logrow = logsheet.createRow(indexrow++);
						DNIS dn = new DNIS();
						dn.setPrefix(prefix);
						dn.setMnumber(mnumber);
						dn.setCname(cname);
						dn.setTicketno(tic);
						dn.setDtfs(dtfs);
						dn.setFilename(multipartFile.getOriginalFilename());
						dn.setUserId(user.getId());
						dn.setDidNumber(didnum);
						if ("".equals(gateway)) {
							gateway = "default";
						}
						dn.setGatewayName(gateway);
						logrow.createCell(0).setCellValue(didnum);

						if (action.equals("ADD")) {
							dbAction = "ADD";
							addDNISLst.add(dn);
							logrow.createCell(1).setCellValue(action);
							logrow.createCell(1).setCellValue(action);
							if (dnisDAO.isDIDExist(didnum)) {
								process = false;
								logrow.createCell(2)
										.setCellValue(
												"DNIS already exist All Addition will be rollback");
							} else if (!dnisDAO.isgroupNameExist(gateway)) {
								process = false;
								logrow.createCell(2)
										.setCellValue(
												"Gateway is missing  All Addition will be rollback");
							} else {
								logrow.createCell(2).setCellValue("DNIS Added");
							}
						} else if ("DELETE".equals(action)) {
							dn.setStatus("Deleted");
							iDelete++;
							logrow.createCell(1).setCellValue(action);
							if (!dnisDAO.isDIDExist(didnum)) {
								logrow.createCell(2)
										.setCellValue(
												"DNIS is not Exist can not perform this operation");
							} else {
								logrow.createCell(2).setCellValue(
										"Successfully Deleted");
								dnisDAO.uploadFileDelete(dn);
							}

						} else if ("MODIFY".equals(action)) {
							iModify++;
							logrow.createCell(1).setCellValue(action);
							if (!dnisDAO.isDIDExist(didnum)) {
								logrow.createCell(2)
										.setCellValue(
												"DNIS is not Exist can not perform this operation");
							} else {
								logrow.createCell(2).setCellValue(
										"Successfully Modify");
								dnisDAO.uploadFileModify(dn);
							}

						} else if ("UNDELETE".equals(action)) {
							dn.setStatus("Active");
							iUndelete++;
							logrow.createCell(1).setCellValue(action);
							if (!dnisDAO.isDIDExist(didnum)) {
								logrow.createCell(2)
										.setCellValue(
												"DNIS is not Exist can not perform this operation");
							} else {
								logrow.createCell(2).setCellValue(
										"Successfully Undeleted");
								dnisDAO.uploadFileDelete(dn);
							}
						}
					}

					if (dbAction.equals("ADD") && process) {
						iAdd = addDNISLst.size();
						dnisDAO.uploadFileData(addDNISLst, "ADD");
					}

					logworkbook.write(response.getOutputStream());
					stream = response.getOutputStream();
					logworkbook.close();
					stream.flush();
					stream.close();
					modelview.addObject("message", iAdd + "||" + iModify + "||"
							+ iDelete + "||" + iUndelete);
					logDAO.LogEvent(user.getFirstName(), user.getLastName(),
							user.getId(), "Uploaded File " + filePath);
				} catch (Exception e) {
					e.printStackTrace();
					modelview.addObject("sessionuser", user);
					modelview.setViewName("dnismanagement");
					modelview.addObject("message", "Please Try Again.");
				}
			} else {
				modelview.addObject("sessionuser", user);
				modelview.setViewName("dnismanagement");
			}

			modelview.addObject("message",
					"Please check the download status file " + exportfilename);
			return modelview;
		}
	}

	@RequestMapping(value = "/downloadReport", method = RequestMethod.POST)
	public ModelAndView downloadReport(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		ModelAndView modelview = new ModelAndView();
		if (session != null) {
			User sessionuser = (User) session.getAttribute("user");
			if (sessionuser != null) {
				User user = (User) session.getAttribute("user");
				modelview.addObject("sessionuser", user);
				modelview.setViewName("dnismanagement");
				ServletOutputStream stream = null;
				String status = request.getParameter("Status");
				String datem = request.getParameter("datem");
				String prefix_mapp = request.getParameter("prefixmapp");
				String file = request.getParameter("file");
				String did = request.getParameter("did");
				String ticnumber = request.getParameter("ticnumber");
				String gatewayname = request.getParameter("gatewayname");

				System.out.println("Values are as con:" + status + ""
						+ prefix_mapp + "" + datem + "" + file + "" + did + ""
						+ ticnumber + "" + gatewayname);

				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition",
						"attachment; filename=Report.xls");

				List<DNIS> downloadReportLst = dnisDAO.getDownloadReportList(
						status, prefix_mapp, file, did, ticnumber, gatewayname,
						datem);
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("Report");
				int rowIndex = 0;
				Row rowheader = sheet.createRow(rowIndex++);
				rowheader.createCell(0).setCellValue("RecordID");
				rowheader.createCell(1).setCellValue("Prefix");
				rowheader.createCell(2).setCellValue("MappedNumber");
				rowheader.createCell(3).setCellValue("CustomerName");
				rowheader.createCell(4).setCellValue("DateCreated");
				rowheader.createCell(5).setCellValue("DateModified");
				rowheader.createCell(6).setCellValue("TicketOrderNum");
				rowheader.createCell(7).setCellValue("DTFS");
				rowheader.createCell(8).setCellValue("Status");
				rowheader.createCell(9).setCellValue("File Name");
				rowheader.createCell(10).setCellValue("userID");
				rowheader.createCell(11).setCellValue("DIDnumber");
				rowheader.createCell(12).setCellValue("GatewayGroupName");
				rowheader.createCell(13).setCellValue("InitialActionBy");
				rowheader.createCell(14).setCellValue("CurrentAction");
				rowheader.createCell(15).setCellValue("RountingID1");
				rowheader.createCell(16).setCellValue("RountingID2");
				rowheader.createCell(17).setCellValue("RountingID3");
				rowheader.createCell(18).setCellValue("RountingID4");
				rowheader.createCell(19).setCellValue("RountingID5");
				rowheader.createCell(20).setCellValue("RountingID6");
				for (DNIS report : downloadReportLst) {
					Row row = sheet.createRow(rowIndex++);
					row.createCell(0).setCellValue((report).getRecordid());
					row.createCell(1).setCellValue((report).getPrefix());
					row.createCell(2).setCellValue((report).getMnumber());
					row.createCell(3).setCellValue((report).getCname());
					row.createCell(4).setCellValue((report).getDatec());
					row.createCell(5).setCellValue((report).getDatem());
					row.createCell(6).setCellValue((report).getTicketno());
					row.createCell(7).setCellValue((report).getDtfs());
					row.createCell(8).setCellValue((report).getStatus());
					row.createCell(9).setCellValue((report).getFile());
					row.createCell(10).setCellValue((report).getUserId());
					row.createCell(11).setCellValue((report).getDidNumber());
					row.createCell(12).setCellValue((report).getGatewayName());
					row.createCell(13)
							.setCellValue((report).getInitialAction());
					row.createCell(14)
							.setCellValue((report).getCurrentAction());
					row.createCell(15).setCellValue((report).getRip1());
					row.createCell(16).setCellValue((report).getRip2());
					row.createCell(17).setCellValue((report).getRip3());
					row.createCell(18).setCellValue((report).getRip4());
					row.createCell(19).setCellValue((report).getRip5());
					row.createCell(20).setCellValue((report).getRip6());
				}

				try {
					workbook.write(response.getOutputStream());
					stream = response.getOutputStream();
					workbook.close();
					stream.flush();
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				logDAO.LogEvent(user.getFirstName(), user.getLastName(),
						user.getId(), "Downloaded Report");
				return modelview;
			} else {
				modelview.setViewName("index");
				return modelview;
			}
		} else {
			modelview.setViewName("index");
			return modelview;
		}

	}

	@RequestMapping(value = "/dnisTemplate", method = RequestMethod.POST)
	public void downloadTemplate(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// Authorized user will download the file
		String dataDirectory = request.getServletContext().getRealPath(
				"/WEB-INF/");
		String fileName = "DNISMangementDataSample.xlsx";
		Path file = Paths.get(dataDirectory, fileName);
		if (Files.exists(file)) {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			try {
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView user(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelview = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null) {
			User sessionuser = (User) session.getAttribute("user");
			if (sessionuser != null) {
				List<User> users = userDAO.getAllUser();
				modelview.addObject("userList", users);
				modelview.addObject("sessionuser", sessionuser);
				modelview.setViewName("user");
				return modelview;
			} else {
				modelview.setViewName("index");
				return modelview;
			}
		} else {
			modelview.setViewName("index");
			return modelview;
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ModelAndView adduser(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		ModelAndView modelview = new ModelAndView();

		if (session != null) {
			User sessionuser = (User) session.getAttribute("user");
			if (sessionuser != null) {
				String action = null;

				if (request.getParameter("action") != null) {
					action = request.getParameter("action");
					if (action.equals("modify")) {
						String id = request.getParameter("userID");
						String firstName = request.getParameter("firstname");
						String lastName = request.getParameter("lastname");
						String userName = request.getParameter("username");
						String password = request.getParameter("password");
						String type = request.getParameter("type");
						User user = new User();
						if (id != null) {
							user = userDAO.getUserById(id);
							user.setFirstName(firstName);
							user.setLastName(lastName);
							user.setType(type);
							user.setUserName(userName);
							if (password != null && !"".equals(password)) {
								user.setPassword(password);
							}
							int use = userDAO.editUser(user);
							if (use == 1) {
								modelview.addObject("message",
										"Record Updated Successfully.");
								modelview.setViewName("user");
							} else {
								modelview.addObject("message",
										"Record Not Updated.");
								modelview.setViewName("user");
							}
						}
						logDAO.LogEvent(sessionuser.getFirstName(),
								sessionuser.getLastName(), sessionuser.getId(),
								"Update User " + user.getFirstName());
					}

					if (action.equals("add")) {
						User user = new User();
						user.setFirstName(request.getParameter("firstname"));
						user.setLastName(request.getParameter("lastname"));
						user.setUserName(request.getParameter("username"));
						user.setPassword(request.getParameter("password"));
						user.setType(request.getParameter("type"));

						int user1 = userDAO.registerUser(user);
						if (user1 == -1) {
							modelview.addObject("message",
									"Username Already Exist.");
							modelview.setViewName("user");
						} else {
							modelview.addObject("message",
									"User Created Successfully.");
							modelview.setViewName("user");
						}
						logDAO.LogEvent(sessionuser.getFirstName(),
								sessionuser.getLastName(), sessionuser.getId(),
								"Added User " + user.getFirstName());
					}
				}

				List<User> users = userDAO.getAllUser();
				modelview.addObject("userList", users);
				modelview.addObject("sessionuser", sessionuser);
				modelview.setViewName("user");
				return modelview;
			} else {
				modelview.setViewName("index");
				return modelview;
			}
		} else {
			modelview.setViewName("index");
			return modelview;
		}
	}

	@RequestMapping(value = "/deleteUser")
	public ModelAndView deleteuser(@RequestParam("id") String id,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		HttpSession session = request.getSession(false);
		User sessionuser = (User) session.getAttribute("user");
		ModelAndView modelview = new ModelAndView();
		int use = userDAO.deleteUser(id);

		if (use == 1) {
			modelview.addObject("message", "User Deleted...");
			modelview.setViewName("user");
		} else {
			modelview.addObject("message", "User Not Deleted.");
			modelview.setViewName("user");
		}

		List<User> users = userDAO.getAllUser();
		modelview.addObject("userList", users);
		modelview.setViewName("user");
		modelview.addObject("sessionuser", sessionuser);
		logDAO.LogEvent(sessionuser.getFirstName(), sessionuser.getLastName(),
				sessionuser.getId(), "Deleted User ");
		return modelview;
	}

	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public ModelAndView logs(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		HttpSession session = request.getSession(false);
		ModelAndView modelview = new ModelAndView();
		if (session != null) {
			User sessionuser = (User) session.getAttribute("user");
			if (sessionuser != null) {

				SimpleDateFormat date = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Calendar now = Calendar.getInstance();
				modelview.addObject("edate", date.format(now.getTime()));
				String dateInString = date.format(new Date());
				String startdate = dateInString.split(" ")[0];
				startdate = startdate + " 00:00:00";
				now.set(Calendar.HOUR, 0);
				now.set(Calendar.MINUTE, 0);
				now.set(Calendar.SECOND, 0);
				now.set(Calendar.HOUR_OF_DAY, 0);
				modelview.addObject("sdate", startdate);

				modelview.setViewName("log");
				modelview.addObject("sessionuser", sessionuser);
				return modelview;
			} else {
				modelview.setViewName("index");
				return modelview;
			}
		} else {
			modelview.setViewName("index");
			return modelview;
		}
	}

	@RequestMapping(value = "/log", method = RequestMethod.POST)
	public ModelAndView logdate(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		HttpSession session = request.getSession(false);
		ModelAndView modelview = new ModelAndView();

		boolean empty = false;
		if (session != null) {
			User sessionuser = (User) session.getAttribute("user");
			if (sessionuser != null) {
				String startdate = request.getParameter("startdate");
				String enddate = request.getParameter("enddate");
				String pageLength = request.getParameter("example_length");
				session.setAttribute("Pagination", pageLength);
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				String pattern = "yyyy-MM-dd";
				String dateInString = new SimpleDateFormat(pattern)
						.format(new Date());
				Calendar cal = Calendar.getInstance();
				if (startdate == null || "".equals(startdate)) {
					startdate = dateInString + " 00:00:00";
					empty = true;
				}
				if (enddate == null || "".equals(enddate)) {
					enddate = df.format(cal.getTime());
				}
				List<Log> logs = logDAO.getLogonDates(startdate, enddate);
				modelview.addObject("logList", logs);
				if (empty) {
					modelview.addObject("sdate", "");
					modelview.addObject("edate", "");
				} else {
					modelview.addObject("sdate", startdate);
					modelview.addObject("edate", enddate);
				}
				modelview.setViewName("log");
				modelview.addObject("sessionuser", sessionuser);
				modelview.addObject("Pagination", pageLength);
				return modelview;
			} else {
				modelview.setViewName("index");
				return modelview;
			}
		} else {
			modelview.setViewName("index");
			return modelview;
		}
	}

	@RequestMapping(value = "/currentaction", method = RequestMethod.GET)
	public ModelAndView addactions(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		ModelAndView modelview = new ModelAndView();
		if (session != null) {
			User sessionuser = (User) session.getAttribute("user");
			if (sessionuser != null) {

				String status = request.getParameter("Status");
				String datem = request.getParameter("datem");
				String prefix_mapp = request.getParameter("prefixmapp");
				String file = request.getParameter("file");
				String did = request.getParameter("did");
				String ticnumber = request.getParameter("ticnumber");
				String gatewayname = request.getParameter("gatewayname");

				List<String> gatways = dnisDAO.getGatewaysfromDb();

				String recid = request.getParameter("modify");
				String recid1 = request.getParameter("add");
				String recid2 = request.getParameter("delete");
				String recid3 = request.getParameter("undelete");

				if (recid != null && !recid.equals("")) {
					dnisDAO.updatemodifyAction(recid, "Active");
				}
				if (recid1 != null && !recid1.equals("")) {
					dnisDAO.updateaddAction(recid1, "Active");
				}
				if (recid2 != null && !recid2.equals("")) {
					dnisDAO.updatedeleteAction(recid2, "Deleted");
				}
				if (recid3 != null && !recid3.equals("")) {
					dnisDAO.updateundeleteAction(recid3, "Active");
				}

				List<DNIS> dnisList = dnisDAO.getDnisByparameter(status,
						prefix_mapp, file, did, ticnumber, gatewayname, datem);

				modelview.addObject("status", status);
				modelview.addObject("datem", datem);
				modelview.addObject("prefixmapp", prefix_mapp);
				modelview.addObject("file", file);
				modelview.addObject("didnum", did);
				modelview.addObject("ticnumber", ticnumber);
				modelview.addObject("gatewayname", gatewayname);
				modelview.addObject("dnisList", dnisList);
				modelview.addObject("sessionuser", sessionuser);
				modelview.addObject("gatways", gatways);
				modelview.setViewName("dnismanagement");
				return modelview;
			} else {
				modelview.setViewName("index");
				return modelview;
			}
		} else {
			modelview.setViewName("index");
			return modelview;
		}
	}

	@RequestMapping(value = "/currentaction", method = RequestMethod.POST)
	public ModelAndView addactionspost(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		User sessionuser = (User) session.getAttribute("user");
		ModelAndView modelview = new ModelAndView();
		String status = request.getParameter("StatusEd");
		// String recid=request.getParameter("recid");
		String prefix = request.getParameter("prefixEd");
		String mapp = request.getParameter("mappEd");
		String file = request.getParameter("fileEd");
		String did = request.getParameter("didEd");
		String ticnumber = request.getParameter("ticnumberEd");
		String gatewayname = request.getParameter("GatewayGroupNameEd");
		/*
		 * String datem = request.getParameter("datemEd"); String prefix_mapp =
		 * request.getParameter("prefixmappEd");
		 */
		List<String> gatways = dnisDAO.getGatewaysfromDb();
		DNIS dnis = new DNIS();
		dnis.setStatus(status);
		dnis.setDidNumber(did);
		dnis.setPrefix(prefix);
		dnis.setMnumber(mapp);
		dnis.setFile(file);
		dnis.setTicketno(ticnumber);
		dnis.setGatewayName(gatewayname);
		dnisDAO.modify(dnis);
		List<DNIS> dnisList = dnisDAO.getDnisByparameter("", "", "", "", "",
				"", "");
		modelview.addObject("dnisList", dnisList);
		modelview.addObject("sessionuser", sessionuser);
		modelview.addObject("gatways", gatways);
		modelview.setViewName("dnismanagement");

		return modelview;
	}

	@RequestMapping(value = "/addaction", method = RequestMethod.GET)
	public ModelAndView addactionget(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelview = new ModelAndView();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<String> gatways = dnisDAO.getGatewaysfromDb();
		modelview.addObject("sessionuser", user);
		modelview.addObject("gatways", gatways);
		modelview.setViewName("addaction");
		return modelview;
	}

	@RequestMapping(value = "/addaction", method = RequestMethod.POST)
	public ModelAndView addAction(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		User sessionuser = (User) session.getAttribute("user");
		ModelAndView modelview = new ModelAndView();

		String tic_number = request.getParameter("TicketOrderNumber");
		String didnum = request.getParameter("DIDNUMBER");
		String dtfs = request.getParameter("DTFS");
		String prefix = request.getParameter("Prefix");
		String mappnum = request.getParameter("MappingNumber");
		String cuname = request.getParameter("CustomerName");
		String gateway = request.getParameter("GatewayGroupName");
		boolean process = true;

		DNIS dn = new DNIS();
		dn.setPrefix(prefix);
		dn.setMnumber(mappnum);
		dn.setCname(cuname);
		dn.setTicketno(tic_number);
		dn.setDtfs(dtfs);
		dn.setFilename("");
		dn.setInitialAction("Manual");
		dn.setUserId(sessionuser.getId());
		dn.setDidNumber(didnum);
		dn.setGatewayName(gateway);
		int i = 0;
		if (dnisDAO.isDIDExist(didnum)) {
			process = false;
			modelview.addObject("message", "DNIS is already exist");
			modelview.setViewName("addaction");
		}
		if (!dnisDAO.isgroupNameExist(gateway)) {
			process = false;
			modelview.addObject("message", "Gateway is not available");
			modelview.setViewName("addaction");
		}
		if (process) {
			i = dnisDAO.addAction(dn);
		}
		List<String> gatways = dnisDAO.getGatewaysfromDb();
		modelview.addObject("gatways", gatways);
		if (i == 1) {
			modelview.addObject("message", "DNIS has beed added successfully");
			modelview.setViewName("addaction");
		}
		logDAO.LogEvent(sessionuser.getFirstName(), sessionuser.getLastName(),
				sessionuser.getId(), "DNIS Action Manually ");

		return modelview;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		logDAO.LogEvent(user.getFirstName(), user.getLastName(), user.getId(),
				"Logout User " + user.getFirstName());
		session.invalidate();
		return "index";
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public String changepasswordget(ModelMap model, HttpServletRequest request) {
		return "changepassword";
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public ModelAndView changepasswordpost(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelview = new ModelAndView();
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		if (userDAO.updateUser(user.getId(), request.getParameter("password")) == 1) {
			modelview.setViewName("dashboard");
			modelview.addObject("message", "Password Changed Successfully.");
			modelview.addObject("sessionuser", user);
			logDAO.LogEvent(user.getFirstName(), user.getLastName(),
					user.getId(), "Password changed by"+" "+user.getFirstName());
			return modelview;
		
		} 
		else {
			modelview.setViewName("dashboard");
			modelview.addObject("sessionuser", user);
			modelview.addObject("message", "Please Try Again.");
		}
		
		return modelview;
	}
	

	@RequestMapping(value = "/uploadconfigFile", method = RequestMethod.POST)
	public ModelAndView uploadconfigpost(ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		ModelAndView modelview = new ModelAndView();
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile multipartFile = multipartRequest.getFile("file1");
				Config config = new Config();
				String filename = multipartFile.getOriginalFilename();
				config.setFile(multipartFile.getOriginalFilename());
				ServletContext context = request.getServletContext();
				File file = new File(context.getRealPath("/") + "/" + filename);
				String dirPat = file.getAbsolutePath().toString();
				String filePath = dirPat.substring(0,
						dirPat.lastIndexOf(File.separator));
				try {
					configDao.uploadconfigFile(filename);
					modelview.setViewName("dnismanagement");
				} catch (Exception e) {
					e.printStackTrace();
					modelview.addObject("sessionuser", user);
					modelview.setViewName("dnismanagement");
					modelview.addObject("message",
							"Duplicate record, Please Try Again.");
				}
				modelview.addObject("sessionuser", user);
				modelview.addObject("message", "Record Added successfully.");
				modelview.setViewName("dnismanagement");
				logDAO.LogEvent(user.getFirstName(), user.getLastName(),
						user.getId(), "Uploaded File " + filePath);
				return modelview;
			} else {
				modelview.setViewName("index");
				return modelview;
			}
		} else {
			modelview.setViewName("index");
			return modelview;
		}
	}

	@RequestMapping(value = "/downloadConfig", method = RequestMethod.POST)
	public ModelAndView downloadConfig(ModelMap model,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, TransformerException,
			ParserConfigurationException, SAXException {
		HttpSession session = request.getSession(false);
		ModelAndView modelview = new ModelAndView();
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				String path1 = path + File.separator + "mpls1" + File.separator
						+ "switch-config.xml";
				System.out.println("Path1="+path1);
				String path2 = path + File.separator + "mpls2" + File.separator
						+ "switch-config.xml";
				String path3 = path + File.separator + "internet"
						+ File.separator + "switch-config.xml";
				File fXmlFile = new File(path1);
				File fXmlFile1 = new File(path2);
				File fXmlFile2 = new File(path3);
				UpdateXML(fXmlFile, path1);
				UpdateXML(fXmlFile1, path2);
				UpdateXML(fXmlFile2, path3);
				modelview.addObject("message", "File Updated Succesfully.");
				modelview.setViewName("dnismanagement");
				modelview.addObject("sessionuser", user);
				modelview.addObject("message", "File Updated Succesfully.");
				modelview.setViewName("dnismanagement");
				logDAO.LogEvent(user.getFirstName(), user.getLastName(),
						user.getId(), "Config Updated Succesfully by "+user.getFirstName() );
				return modelview;
			} else {
				modelview.setViewName("index");
				return modelview;
			}
		} else {
			modelview.setViewName("index");
			return modelview;
		}
	}

	public void UpdateXML(File fXmlFile, String path)
			throws TransformerException, ParserConfigurationException,
			SAXException, IOException {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		Node dnisMap = doc.getElementsByTagName("dnis-map").item(0);
		Node gatewayGroupRules = doc.getElementsByTagName("gatewayGroupRules")
				.item(0);
		Node gatewayName = doc.getElementsByTagName("gatewayGroups").item(0);
		Node gateways = doc.getElementsByTagName("gateways").item(0);
		NodeList maps = dnisMap.getChildNodes();
		NodeList getwayMapsmaps = gatewayGroupRules.getChildNodes();
		NodeList getwayNameMap = gatewayName.getChildNodes();
		NodeList getwaysMap = gateways.getChildNodes();
		int len = maps.getLength();
		for (int temp = 0; temp < len; temp++) {
			Node nNode = maps.item(temp);
			if (nNode != null) {
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					dnisMap.removeChild(nNode);
				}
			}
		}
		for(int temp1=0;temp1<len; temp1++){
			for (int temp = 0; temp < len; temp++) {
				Node nNode = maps.item(temp);
				if (nNode != null) {
					dnisMap.removeChild(nNode);
				}
			}
			}
		len = getwayMapsmaps.getLength();
		for (int temp = 0; temp < len; temp++) {
			Node nNode = getwayMapsmaps.item(temp);
			if (nNode != null) {
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					gatewayGroupRules.removeChild(nNode);
				}
			}
		}
		for (int temp = 0; temp < len; temp++) {
			Node nNode = getwayMapsmaps.item(temp);
			if (nNode != null) {
				gatewayGroupRules.removeChild(nNode);
			}
		}
		for (int temp = 0; temp < len; temp++) {
			Node nNode = getwayMapsmaps.item(temp);
			if (nNode != null) {
				gatewayGroupRules.removeChild(nNode);
			}
		}
		
		for (int temp = 0; temp < len; temp++) {
			Node nNode = getwayMapsmaps.item(temp);
			if (nNode != null) {
				gatewayGroupRules.removeChild(nNode);
			}
		}
		len = getwayMapsmaps.getLength();
		for (int temp = 0; temp < len; temp++) {
			Node nNode = getwayNameMap.item(temp);
			if (nNode != null) {
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					gatewayName.removeChild(nNode);
				}
			}
		}
		for (int temp = 0; temp < len; temp++) {
			Node nNode = getwayNameMap.item(temp);
			if (nNode != null) {
				gatewayName.removeChild(nNode);
			}
		}
		for (int temp = 0; temp < len; temp++) {
			Node nNode = getwayNameMap.item(temp);
			if (nNode != null) {
				gatewayName.removeChild(nNode);
			}
		}
		len = getwaysMap.getLength();
		for (int temp = 0; temp < len; temp++) {
			Node nNode = getwaysMap.item(temp);
			if (nNode != null) {
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					gateways.removeChild(nNode);
				}
			}
		}
		for (int temp = 0; temp < len; temp++) {
			Node nNode = getwaysMap.item(temp);
			if (nNode != null) {
				gateways.removeChild(nNode);
			}
		}
		for (int temp = 0; temp < len; temp++) {
			Node nNode = getwaysMap.item(temp);
			if (nNode != null) {
				gateways.removeChild(nNode);
			}
		}

		List<DNISTarget> dnisLst = dnisDAO.getAllDnis();
		for (DNISTarget dnisTarget : dnisLst) {
			Element newmap = doc.createElement("map");
			newmap.setAttribute("dnis", dnisTarget.getDNIS());
			newmap.setAttribute("target", dnisTarget.getTarget());
			dnisMap.appendChild(newmap);

		}
		List<DNISTarget> gatewayRule = dnisDAO.getAllgetwaysGroup();
		List<String> checkDNIS = new ArrayList<String>();
		String sdnisTarget = "";
		for (DNISTarget dnisTarget : gatewayRule) {
			Element newmap = doc.createElement("rule");	
			sdnisTarget = dnisTarget.getTarget();
			if(!checkDNIS.contains(sdnisTarget)){
			checkDNIS.add(dnisTarget.getTarget());
			newmap.setAttribute("calleeUserExpr", dnisTarget.getTarget());
			newmap.setAttribute("gatewayGroupId", dnisTarget.getDNIS());
			gatewayGroupRules.appendChild(newmap);
			}
		}


		List<GATEWAYS> gatewayNameM = dnisDAO.getwaysfromName();
		for (GATEWAYS gatways : gatewayNameM) {

			Element newmap = doc.createElement("group");
			newmap.setAttribute("selectionStrategy", "DISTRIBUTION");
			newmap.setAttribute("id", gatways.getGatewayGroupName());

			Element newgatway = doc.createElement("gateway");
			newgatway.setAttribute("id", gatways.getRoutingIP1());
			newgatway.setAttribute("value", gatways.getRoutevalue1());
			newmap.appendChild(newgatway);

			Element newgatway1 = doc.createElement("gateway");
			newgatway1.setAttribute("id", gatways.getRoutingIP2());
			newgatway1.setAttribute("value", gatways.getRoutevalue2());
			newmap.appendChild(newgatway1);

			Element newgatway2 = doc.createElement("gateway");
			newgatway2.setAttribute("id", gatways.getRoutingIP3());
			newgatway2.setAttribute("value", gatways.getRoutevalue3());
			newmap.appendChild(newgatway2);

			Element newgatway3 = doc.createElement("gateway");
			newgatway3.setAttribute("id", gatways.getRoutingIP4());
			newgatway3.setAttribute("value", gatways.getRoutevalue4());
			newmap.appendChild(newgatway3);

			Element newgatway4 = doc.createElement("gateway");
			newgatway4.setAttribute("id", gatways.getRoutingIP5());
			newgatway4.setAttribute("value", gatways.getRoutevalue5());
			newmap.appendChild(newgatway4);

			Element newgatway5 = doc.createElement("gateway");
			newgatway5.setAttribute("id", gatways.getRoutingIP6());
			newgatway5.setAttribute("value", gatways.getRoutevalue6());
			newmap.appendChild(newgatway5);
			gatewayName.appendChild(newmap);
		}

		List<GATEWAYSHost> gatewaysList = dnisDAO.getgetways();
		for (GATEWAYSHost gatewayhost : gatewaysList) {
			Element newmap = doc.createElement("gateway");
			newmap.setAttribute("id", gatewayhost.getId());
			newmap.setAttribute("host", gatewayhost.getHost());
			newmap.setAttribute("trunkGroupId", gatewayhost.getTrunkGroupId());
			gateways.appendChild(newmap);
		}

		DOMSource source = new DOMSource(doc);
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		StreamResult result = new StreamResult(new File(path));
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(source, result);
	}
}