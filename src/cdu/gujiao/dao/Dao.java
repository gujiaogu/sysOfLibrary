package cdu.gujiao.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cdu.gujiao.model.Back;
import cdu.gujiao.model.BookInfo;
import cdu.gujiao.model.BookType;
import cdu.gujiao.model.Borrow;
import cdu.gujiao.model.Operater;
import cdu.gujiao.model.Order;
import cdu.gujiao.model.OrderAndBookInfo;
import cdu.gujiao.model.Reader;
import cdu.gujiao.model.user;

public class Dao {
	protected static String dbClassName = 
			"com.microsoft.sqlserver.jdbc.SQLServerDriver";					//���ݿ�����������
	protected static String dbUrl = "jdbc:sqlserver://localhost:1309;"
							+ "DatabaseName=db_library;";					//���ݿ�����URL
	protected static String dbUser = "sa";									//���ݿ��û���
	protected static String dbPwd = "gujiaogu";								//���ݿ�����
	private static Connection conn = null;									//���ݿ����Ӷ���
	
	private Dao() {															//Ĭ�Ϲ��캯��
		try {
			if (conn == null) {												//������Ӷ���Ϊ��
				Class.forName(dbClassName);									//����������
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);	//������Ӷ���
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
	
	private static ResultSet executeQuery(String sql) {						//��ѯ����
		try {
			if(conn==null)  new Dao();  									//������Ӷ���Ϊ�գ������µ��ù��췽��
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(sql);			//ִ�в�ѯ
		} catch (SQLException e) {
			e.printStackTrace();
			return null;													//����nullֵ
		} finally {
			
		}
	}
	
	private static int executeUpdate(String sql) {							//���·���
		try {
			if(conn==null)  new Dao();										//������Ӷ���Ϊ�գ������µ��ù��췽��
			return conn.createStatement().executeUpdate(sql);				//ִ�и���
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
		}
	}
	
	public static void close() {											//�رշ���
		try {
			conn.close();													//�ر����Ӷ���		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn = null;													//�������Ӷ���Ϊnullֵ
		}
	}
	
	/*
	 * ����Ա��¼����
	 */
	public static Operater checkOperater(String name, String password) {
		Operater operater=new Operater();									//����Ա��Ϣ����
		String sql = "select *  from tb_operator where name='" + name
				+ "' and password='" + password + "'and admin=1";			//��ѯ�ַ���
		ResultSet rs = Dao.executeQuery(sql);								//ִ�в�ѯ
		try {
			while (rs.next()) {												//�����ѯ���˼�¼
				operater.setId(rs.getString("id"));							//���ò���Ա���
				operater.setName(rs.getString("name"));						//���ò���Ա�û���
				operater.setGrade(rs.getString("admin"));					//���ò���Ա�ȼ�
				operater.setPassword(rs.getString("password"));				//���ù���Ա����
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();														//�ر����Ӷ���
		return operater;													//���ز���Ա��Ϣ����
	}
	/*
	 * ��ѯͼ����𷽷�
	 */
	public static List selectBookCategory() {
		List list=new ArrayList();
		String sql = "select *  from tb_bookType";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				BookType bookType=new BookType();
				bookType.setId(rs.getString("id"));
				bookType.setTypeName(rs.getString("typeName"));
				bookType.setDays(rs.getString("days"));
				bookType.setFk(rs.getString("fk"));
				list.add(bookType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
		
	}
	public static List selectBookCategory(String bookType) {
		List list=new ArrayList();
		String sql = "select days  from tb_bookType where typeName='"+bookType+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				BookType type=new BookType();
				type.setDays(rs.getString("days"));
				list.add(type);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
		
	}
	/*
	 * ͼ��������ز���
	 * 
	 */
	public static int InsertBookType(String bookTypeName,String days,Double fk){
		int i=0;
		try{
			String sql="insert into tb_bookType(typeName,days,fk) values('"+bookTypeName+"','"+days+"',"+fk+")";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	public static int UpdatebookType(String id,String typeName,String days,String fk){
		int i=0;
		try{
			String sql="update tb_bookType set typeName='"+typeName+"',days='"+days+"',fk='"+fk+"' where id='"+id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	public static List selectBookTypeFk(String bookType) {//ȡÿ���鳬���涨ʱ�䷣����
		List list=new ArrayList();
		String sql = "select *  from tb_bookType where typeName='"+bookType+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				BookType type=new BookType();
				type.setFk(rs.getString("fk"));
				type.setDays(rs.getString("days"));
				list.add(type);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
		
	}
	/*
	 * ͼ����Ϣ����ز���
	 */
	/*
	 * ����ͼ����Ϣ����
	 */
	public static int Insertbook(String ISBN,String typeId,String bookname,
			String writer,String translator,String publisher,Date date,Double price){
		int i=0;
		try{
			String sql="insert into tb_bookInfo(ISBN,typeId,bookname,writer,translator," +
					"publisher,date,price) values('"+ISBN+"','"+typeId+"','"+bookname+"'," +
					"'"+writer+"','"+translator+"','"+publisher+"','"+date+"',"+price+")";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		Dao.close();	//�ر����Ӷ���	
		return i;		//���ظ��¼�¼��
	}
	/*
	 * ��ѯͼ�������Ϣ
	 * 
	 */

	public static List selectBookInfo() {
		List list=new ArrayList();
		String sql = "select * from tb_bookInfo";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				BookInfo bookinfo=new BookInfo();
				bookinfo.setISBN(rs.getString("ISBN"));
				bookinfo.setTypeid(rs.getString("typeid"));
				bookinfo.setBookname(rs.getString("bookname"));
				bookinfo.setWriter(rs.getString("writer"));
				bookinfo.setTranslator(rs.getString("translator"));
				bookinfo.setPublisher(rs.getString("publisher"));
				bookinfo.setDate(rs.getDate("date"));
				bookinfo.setPrice(rs.getDouble("price"));
				list.add(bookinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	/*
	 * ����ͼ���Ų�ѯͼ��
	 * 
	 */
	public static List selectBookInfo(String ISBN) {
		List list=new ArrayList();									//�������в�ѯ���鼮��Ϣ
		String sql = "select *  from tb_bookInfo where ISBN='"+ISBN+"'";
		ResultSet rs = Dao.executeQuery(sql);						//ִ�в�ѯ
		try {
			while (rs.next()) {										//���������
				BookInfo bookinfo=new BookInfo();					//�鼮��Ϣ����
				bookinfo.setISBN(rs.getString("ISBN"));				//�����鼮���
				bookinfo.setTypeid(rs.getString("typeid"));			//���������
				bookinfo.setBookname(rs.getString("bookname"));		//�����鼮����
				bookinfo.setWriter(rs.getString("writer"));			//��������
				bookinfo.setTranslator(rs.getString("translator"));	//��������
				bookinfo.setPublisher(rs.getString("publisher"));	//���ó�����
				bookinfo.setDate(rs.getDate("date"));				//���ó�������
				bookinfo.setPrice(rs.getDouble("price"));			//���ü۸�
				list.add(bookinfo);									//����鼮��Ϣ
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();												//�ر����Ӷ���
		return list;												//���ز�ѯ�����
	}
	/*
	 * �޸�ͼ����Ϣ����
	 */
	public static int Updatebook(String ISBN,String typeId,String bookname,
			String writer,String translator,String publisher,Date date,Double price){
		int i=0;//���¼�¼��
		try{
			String sql="update tb_bookInfo set ISBN='"+ISBN+"',typeId='"+typeId+"',bookname='"
					+bookname+"',writer='"+writer+"',translator='"+translator+"',publisher='"
					+publisher+"',date='"+date+"',price="+price+" where ISBN='"+ISBN+"'";
			i=Dao.executeUpdate(sql);//ִ�и���
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();//�ر����Ӷ���
		return i;//���ظ��¼�¼��
	}
	/*
	 * �Զ�����Ϣ��ִ�е���ز���
	 */
	public static int InsertReader(String name,String sex,String age,String identityCard,Date date,String maxNum,String tel,Double keepMoney,String zj,String zy,Date bztime,String ISBN){
		int i=0;
		try{
			String sql="insert into tb_reader(name,sex,age,identityCard,date,maxNum,tel,keepMoney,zj,zy,bztime,ISBN) values('"+name+"','"+sex+"','"+age+"','"+identityCard+"','"+date+"','"+maxNum+"','"+tel+"',"+keepMoney+",'"+zj+"','"+zy+"','"+bztime+"','"+ISBN+"')";
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	public static List selectReader() {
		List list=new ArrayList();
		String sql = "select *  from tb_reader";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Reader reader=new Reader();
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setAge(rs.getString("age"));
				reader.setIdentityCard(rs.getString("identityCard"));
				reader.setDate(rs.getDate("date"));
				reader.setMaxNum(rs.getString("maxNum"));
				reader.setTel(rs.getString("tel"));
				reader.setKeepMoney(rs.getDouble("keepMoney"));
				reader.setZj(rs.getInt("zj"));
				reader.setZy(rs.getString("zy"));
				reader.setISBN(rs.getString("ISBN"));
				reader.setBztime(rs.getDate("bztime"));
				list.add(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	public static List selectReader(String readerISBN) {
		List list=new ArrayList();
		String sql = "select *  from tb_reader where ISBN='"+readerISBN+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Reader reader=new Reader();
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setAge(rs.getString("age"));
				reader.setIdentityCard(rs.getString("identityCard"));
				reader.setDate(rs.getDate("date"));
				reader.setMaxNum(rs.getString("maxNum"));
				reader.setTel(rs.getString("tel"));
				reader.setKeepMoney(rs.getDouble("keepMoney"));
				reader.setZj(rs.getInt("zj"));
				reader.setZy(rs.getString("zy"));
				reader.setISBN(rs.getString("ISBN"));
				reader.setBztime(rs.getDate("bztime"));
				list.add(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	public static int UpdateReader(String id,String name,String sex,String age,String identityCard,Date date,String maxNum,String tel,Double keepMoney,String zj,String zy,Date bztime,String ISBN){
		int i=0;
		try{
			String sql="update tb_reader set name='"+name+"',sex='"+sex+"',age='"+age+"',identityCard='"+identityCard+"',date='"+date+"',maxNum='"+maxNum+"',tel='"+tel+"',keepMoney="+keepMoney+",zj='"+zj+"',zy='"+zy+"',bztime='"+bztime+"'where ISBN='"+ISBN+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	public static int DelReader(String ISBN){
		int i=0;
		try{
			String sql="delete from tb_reader where ISBN='"+ISBN+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	/**
	 * @description		�Զ�����Ϣ�����
	 * @author			����	
	 * @createDate		2011-3-7
	 * @param	
	 * @return					
	 * @see						
	 */
	public static int InsertBookOrder(String ISBN,Date date,String number,String operator,String checkAndAccept,Double zk){
		int i=0;
		try{
			String sql="insert into tb_order(ISBN,date,number,operator,checkAndAccept,zk) values('"+ISBN+"','"+date+"','"+number+"','"+operator+"',"+checkAndAccept+",'"+zk+"')";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
		
	}
	public static List selectBookOrder() {
		List list=new ArrayList();
		String sql = "SELECT * FROM tb_order a INNER JOIN tb_bookInfo b ON a.ISBN = b.ISBN";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				OrderAndBookInfo order=new OrderAndBookInfo();
				order.setISBN(rs.getString(1));
				order.setOrderdate(rs.getDate(2));
				order.setNumber(rs.getString(3));
				order.setOperator(rs.getString(4));
				order.setCheckAndAccept(rs.getString(5));
				order.setZk(rs.getDouble(6));
				order.setTypeId(rs.getString(8));
				order.setBookname(rs.getString(9));
				order.setWriter(rs.getString(10));
				order.setTraslator(rs.getString(11));
				order.setPublisher(rs.getString(12));
				order.setDate(rs.getDate(13));
				order.setPrice(rs.getDouble(14));
				list.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	/**
	 * @description		����ͼ���Ų�ѯ������Ϣ
	 * @author			����	
	 * @createDate		2011-3-7
	 * @param			ISBN ͼ����
	 * @return			List ��ѯ��¼�����		
	 * @see						
	 */
	public static List selectBookOrder(String ISBN) {
		List list=new ArrayList();
		String sql = "SELECT * FROM tb_order where ISBN='"+ISBN+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Order order=new Order();
				order.setISBN(rs.getString("ISBN"));
				order.setDate(rs.getDate("date"));
				order.setNumber(rs.getString("number"));
				order.setOperator(rs.getString("operator"));
				order.setZk("zk");
				order.setCheckAndAccept("checkAndAccept");
				list.add(order);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	public static int UpdateCheckBookOrder(String ISBN){
		int i=0;
		try{
			String sql="update tb_order set checkAndAccept=0 where ISBN='"+ISBN+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
		
	}
	/*
	 * �Խ��ı���в���
	 */
	public static int InsertBookBorrow(String bookISBN,String readerISBN,String operatorId,Timestamp borrowDate,Timestamp backDate){
		int i=0;
		try{
			String sql="insert into tb_borrow(bookISBN,readerISBN,operatorId,borrowDate,backDate)values('"+bookISBN+"','"+readerISBN+"','"+operatorId+"','"+borrowDate+"','"+backDate+"')";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
		
	}
	public static List selectBorrow(String readerISBN) {
		List list=new ArrayList();
		String sql = "select *  from tb_borrow where readerISBN='"+readerISBN+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Borrow borrow=new Borrow();
				borrow.setId(rs.getInt("id"));
				borrow.setBookISBN(rs.getString("bookISBN"));
				borrow.setReaderISBN(rs.getString("readerISBN"));
				borrow.setBorrowDate(rs.getString("borrowDate"));
				borrow.setBackDate(rs.getString("backDate"));
				borrow.setBookName(rs.getString("borrowBookName"));
				list.add(borrow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	/*
	 * ��ѯ�������ݣ�tb_bookinfo tb_reader tb_borrow֮��Ĳ�ѯ
	 */
	public static List selectBookBack(String readerISBN) {
		List list=new ArrayList();
		String sql = "SELECT a.ISBN AS bookISBN, a.bookname, a.typeId ,b.id,b.operatorId, b.borrowDate, b.backDate, c.name AS readerName, c.ISBN AS readerISBN FROM tb_bookInfo a INNER JOIN tb_borrow b ON a.ISBN = b.bookISBN INNER JOIN tb_reader c ON b.readerISBN = c.ISBN WHERE (c.ISBN = '"+readerISBN+"' and isback=1)";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Back back=new Back();
				back.setBookISBN(rs.getString("bookISBN"));
				back.setBookname(rs.getString("bookname"));
				back.setTypeId(rs.getInt("typeId"));
				back.setOperatorId(rs.getString("operatorId"));
				back.setBorrowDate(rs.getString("borrowDate"));
				back.setBackDate(rs.getString("backDate"));
				back.setReaderName(rs.getString("readerName"));
				back.setReaderISBN(rs.getString("readerISBN"));
				back.setId(rs.getInt("id"));
				list.add(back);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	public static int UpdateBookBack(String bookISBN,String readerISBN,int id){//�黹ͼ�����
		int i=0;
		try{
			String sql="update tb_borrow set isback=0 where bookISBN='"+bookISBN+"'and readerISBN='"+readerISBN+"' and id="+id+"";
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
		
	}
	
	
	/**
	 * @description		��ѯȫ��ͼ����Ϣ
	 * @author			����	
	 * @createDate		2011-3-3
	 * @param			
	 * @return			List ȫ��ͼ����Ϣ�Ľ����		
	 *	
	 * @see						
	 */
	public static List selectbooksearch() {
		List list=new ArrayList();
		String sql = "select *  from tb_bookInfo";
		ResultSet s = Dao.executeQuery(sql);
		try {
			while (s.next()) {
				BookInfo bookinfo=new BookInfo();
				bookinfo.setISBN(s.getString(1));
				bookinfo.setTypeid(s.getString(2));
				bookinfo.setBookname(s.getString(3));
				bookinfo.setWriter(s.getString(4));
				bookinfo.setTranslator(s.getString(5));
				bookinfo.setPublisher(s.getString(6));
				bookinfo.setDate(s.getDate(7));
				bookinfo.setPrice(s.getDouble(8));
				list.add(bookinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	/**
	 * @description		�����鼮����tb_bookInfo�в�ѯ��Ӧ��¼
	 * @author			����	
	 * @createDate		2011-3-3
	 * @param			String bookname  �鼮ȫ���򲿷���
	 * @return			List �����
	 *	
	 * @see						
	 */
	public static List selectbookmohu(String bookname){
		List list=new ArrayList();
		String sql="select * from tb_bookInfo where bookname like '%"+bookname+"%'";
		ResultSet s=Dao.executeQuery(sql);
		try {
			while(s.next()){
				BookInfo bookinfo=new BookInfo();
				bookinfo.setISBN(s.getString(1));
				bookinfo.setTypeid(s.getString(2));
				bookinfo.setBookname(s.getString(3));
				bookinfo.setWriter(s.getString(4));
				bookinfo.setTranslator(s.getString(5));
				bookinfo.setPublisher(s.getString(6));
				bookinfo.setDate(s.getDate(7));
				bookinfo.setPrice(s.getDouble(8));
				list.add(bookinfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * @description		������������tb_bookInfo�в�ѯ��Ӧ��¼
	 * @author			����	
	 * @createDate		2011-3-3
	 * @param			String writer ���������򲿷���
	 * @return			List �����	
	 *	
	 * @see						
	 */
	public static List selectbookmohuwriter(String writer){
		List list=new ArrayList();
		String sql="select * from tb_bookInfo where writer like '%"+writer+"%'";
		ResultSet s=Dao.executeQuery(sql);
		try {
			while(s.next()){
				BookInfo bookinfo=new BookInfo();
				bookinfo.setISBN(s.getString(1));
				bookinfo.setTypeid(s.getString(2));
				bookinfo.setBookname(s.getString(3));
				bookinfo.setWriter(s.getString(4));
				bookinfo.setTranslator(s.getString(5));
				bookinfo.setPublisher(s.getString(6));
				bookinfo.setDate(s.getDate(7));
				bookinfo.setPrice(s.getDouble(8));
				list.add(bookinfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
		
	}
	public static int Insertoperator(String name,String sex,int age,String identityCard,Date workdate,String tel,String password){
		int i=0;
		try{
			String sql="insert into tb_operator(name,sex,age,identityCard,workdate,tel,password) values('"+name+"','"+sex+"',"+age+",'"+identityCard+"','"+workdate+"','"+tel+"','"+password+"')";
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static List selectuser() {
		List list=new ArrayList();
		String sql = "select id,name,sex,age,identityCard,workdate,tel,password  from tb_operator where admin=0";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				user user=new user();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setSex(rs.getString(3));
				user.setAge(rs.getInt(4));
				user.setIdentityCard(rs.getString(5));
				user.setWorkdate(rs.getDate(6));
				user.setTel(rs.getString(7));
				user.setPassword(rs.getString(8));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	public static int Deluser(int id){
		int i=0;
		try{
			String sql="delete from tb_operator where id='"+id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static int Updateuser(int id,String name,String sex,int age,String identityCard,Date workdate,String tel,String password){
		int i=0;
		try{
			String sql="update tb_operator set name='"+name+"',sex='"+sex+"',age="+age+",identityCard='"+identityCard+"',workdate='"+workdate+"',tel='"+tel+"',password='"+password+"' where id='"+id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static int Updatepass(String password,String name){
		int i=0;
		try{
			String sql="update tb_operator set password='"+password+"' where name='"+name+"'";
			
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
}