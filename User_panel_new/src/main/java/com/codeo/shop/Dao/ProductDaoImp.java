package com.codeo.shop.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.codeo.shop.dbutil.ConnectionProvider;
import com.codeo.shop.entity.Cart;
import com.codeo.shop.entity.Product;
import com.codeo.shop.entity.Blog;

public class ProductDaoImp implements ProductDao {
	
	private static final String insert_Product ="insert into product_operation(prod_name, prod_description, prod_price, prod_discount, prod_quantity,prod_imageName,cid) values(?,?,?,?,?,?,?)";
	private static final String selct_product ="SELECT * FROM product_operation ";
	private static final String select_blog ="SELECT * FROM myblogs ";
    private static final String Edit_product ="select * from product_operation where prod_id=?";
    //private static final String Update_product = "UPDATE product_operation SET name = '"+product.getProd_name()+"', "+ "description = '"+product.getProd_description()+"',  price = '"+product.getProd_price()+"', discount = '"+product.getProd_discount()+"',quantity = '"+product.getProd_quantity()+"',image = '"+product.getProd_imageName()+"' where id="+product.getId();
	
	Connection con = null;
    PreparedStatement preparedStatement  =null;
    Statement statement = null;
    
    public ProductDaoImp(Connection con)
    {
    	this.con=con;
    	System.out.println("this is cunstructor con:"+this.con);
    }
    
    public ProductDaoImp()
    {
    	
    }
    
    //insert product ----------------
	public boolean addProduct(Product product) {
		  PreparedStatement psmt = null;
		  con=ConnectionProvider.getconnection();
		  boolean flag = false;
		
         if(con !=null) {
			try {
			psmt = con.prepareStatement(insert_Product);
			
				if(psmt!=null)
				{
					psmt.setString(1, product.getProd_name());
					psmt.setString(2, product.getProd_description());
					psmt.setString(3, product.getProd_price());
					psmt.setString(4, product.getProd_discount());
					psmt.setString(5, product.getProd_quantity());
					psmt.setString(6, product.getProd_imageName());
					psmt.setInt(7, product.getCid());
						}
				System.out.println(psmt);
			    int result = psmt.executeUpdate(); 
				System.out.println("result is "+result);
				flag = true;
			} 
             catch (SQLException e) {
				e.printStackTrace();
			}  }
	      	return flag;
	   }
    //updating product ----------
	  public boolean update(Product product) {
		boolean flag = false;
		//try {
			//String Update_product = "UPDATE product_operation set name = '"+product.getProd_name()+"', "+ "description = '"+product.getProd_description()+"',  price = '"+product.getProd_price()+"', discount = '"+product.getProd_discount()+"',quantity = '"+product.getProd_quantity()+"',image = '"+product.getProd_imageName()+"' where id="+product.getId();
			//con = ConnectionProvider.getconnection();
			//preparedStatement = con.prepareStatement(Update_product);
			//preparedStatement.executeUpdate();
			Connection con = ConnectionProvider.getconnection();
			String update_query="update product_operation set prod_name=?,prod_description=?,prod_price=?,prod_discount=?,prod_quantity=?,prod_imageName=?,cid=? where  prod_id=?";
			 System.out.println(update_query);
			
			 try (PreparedStatement stmt = con.prepareStatement(update_query)) {

	            stmt.setString(1, product.getProd_name());
	            stmt.setString(2, product.getProd_description());
	            stmt.setString(3, product.getProd_price());
	            stmt.setString(4, product.getProd_discount());
	            stmt.setString(5, product.getProd_quantity());
	            stmt.setString(6, product.getProd_imageName());
	               stmt.setInt(7, product.getCid());
	               stmt.setInt(8, product.getId());

	            int rowsUpdated= stmt.executeUpdate();
			    flag = true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	   
	   //get all product ----------------
	 public List <Product> getAllProducts()
		{
			List<Product> list = new ArrayList<Product>();
			Product product = null;
	Connection con = ConnectionProvider.getconnection();
	try {
		Statement statement = con.createStatement();
		ResultSet resultset =null;
		resultset =statement.executeQuery(selct_product);
		//prod_id, prod_name, prod_description, prod_price, prod_discount, prod_quantity, prod_imageName
		while(resultset.next())
		{
			product = new Product();
			product.setId(resultset.getInt("prod_id"));
			product.setProd_name(resultset.getString("prod_name"));
			product.setProd_description(resultset.getString("prod_description"));
			product.setProd_price(resultset.getString("prod_price"));
			product.setProd_discount(resultset.getString("prod_discount"));
			product.setProd_imageName(resultset.getString("prod_imageName"));
		    list.add(product);
		}
	} 
	catch (SQLException e) {
		e.printStackTrace();
	}
		return list;
		}
	  
   //delete query---------
	
	public boolean delete(int id) {
	 boolean status =false;
		String delete_query="DELETE  FROM product_operation where prod_id="+id;
		con = ConnectionProvider.getconnection();
		try {
			preparedStatement = con.prepareStatement(delete_query);
			preparedStatement.executeUpdate();
			status =true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return true;
	}
	/*
	@Override
	public Product editProd(int id) {
		
		Product product = null;
		ResultSet resultset= null;
		 PreparedStatement stmt = null;
		   
		 con= ConnectionProvider.getconnection();
		try {
			  
			 stmt = con.prepareStatement(Edit_product);
		    // stmt.setInt(1, id);
			if(resultset.next())
			{
				//product.setId(resultset.getInt("prod_id"));
				//product.setProd_name(resultset.getString("prod_name"));
				//product.setProd_description(resultset.getString("prod_description"));
				//product.setProd_price(resultset.getString("prod_price"));
				//product.setProd_discount(resultset.getString("prod_discount"));
				//product.setProd_quantity(resultset.getString("prod_quantity"));
				//product.setProd_imageName(resultset.getString("prod_imageName"));
				//int id = resultset.getInt("prod_id");
				String prod_name = resultset.getString("prod_name");
				String prod_description= resultset.getString("prod_description");
				String prod_price= resultset.getString("prod_price");
				String prod_discount= resultset.getString("prod_discount");
				String prod_quantity= resultset.getString("prod_quantity");
				String prod_imageName= resultset.getString("prod_imageName");
				int cid = resultset.getInt("cid");
				product = new Product(prod_name,prod_description,prod_price,prod_discount,prod_quantity,prod_imageName,cid);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	   }
	   
	   */
	
	//for sorting of categories 
	  public List <Product> getAllProductsById(int id)
			{
				List<Product> list = new ArrayList<Product>();
				Product product = null;
		Connection con = ConnectionProvider.getconnection();
		String  getproductById = "select * from product_operation where cid ="+id;
	
		try {
			Statement statement = con.createStatement();
			ResultSet resultset =null;
			resultset =statement.executeQuery(getproductById);
			
			//prod_id, prod_name, prod_description, prod_price, prod_discount, prod_quantity, prod_imageName
			while(resultset.next())
			{
				product = new Product();
				product.setId(resultset.getInt("prod_id"));
				product.setProd_name(resultset.getString("prod_name"));
				product.setProd_description(resultset.getString("prod_description"));
				product.setProd_price(resultset.getString("prod_price"));
				product.setProd_discount(resultset.getString("prod_discount"));
				product.setProd_imageName(resultset.getString("prod_imageName"));
			
				list.add(product);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
			return list;
			}
	 
	 //for sorting of product image details 
	 
	 public List <Product> getAllProductsForDetails(int id)
			{
				List<Product> list = new ArrayList<Product>();
				Product product = null;
		        Connection con = ConnectionProvider.getconnection();
		        String  getproductById = "select * from product_operation where prod_id ="+id;
	
		try {
			Statement statement = con.createStatement();
			ResultSet resultset =null;
			
			resultset =statement.executeQuery(getproductById);
			//prod_id, prod_name, prod_description, prod_price, prod_discount, prod_quantity, prod_imageName
			while(resultset.next())
			{
				product = new Product();
				product.setId(resultset.getInt("prod_id"));
				product.setProd_name(resultset.getString("prod_name"));
				product.setProd_description(resultset.getString("prod_description"));
				product.setProd_price(resultset.getString("prod_price"));
				product.setProd_discount(resultset.getString("prod_discount"));
				product.setProd_imageName(resultset.getString("prod_imageName"));
				product.setCid(resultset.getInt("cid"));
			
				list.add(product);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
			return list;
				
		}
	
	
	public Product getProductById(int id) {
		
	 	
		
		return null;
	}
	
	
	public static void updateproduct(int id, String prod_name, String prod_description, String prod_price,
			String prod_discount, String prod_quantity, String prod_imageName, int cid) {
		
		Connection con = ConnectionProvider.getconnection();
		String update_query="update product_operation set prod_name=?,prod_description=?,prod_price=?,prod_discount=?,prod_quantity=?,prod_imageName=?,cid=? where  prod_id=?";
		 System.out.println(update_query);
		 
		 try (PreparedStatement stmt = con.prepareStatement(update_query)) {

            stmt.setString(1, prod_name);
            stmt.setString(2, prod_description);
            stmt.setString(3, prod_price);
            stmt.setString(4, prod_discount);
            stmt.setString(5, prod_quantity);
            stmt.setString(6, prod_imageName);
            stmt.setInt(7, cid);
            stmt.setInt(8, id);

            int rowsUpdated= stmt.executeUpdate();
         }
	catch(Exception e)
	{
		e.printStackTrace();
	}
		}
	
	
	public Product getProduct(int prod_id) {
	    Connection con = ConnectionProvider.getconnection();
	    PreparedStatement stmt = null;
	    Product product = new Product();

	    try {
	        stmt = con.prepareStatement("SELECT * FROM product_operation WHERE prod_id= ?");
	        stmt.setInt(1, prod_id);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            product.setId(rs.getInt("prod_id"));
	            product.setProd_name(rs.getString("prod_name"));
	            product.setProd_description(rs.getString("prod_description"));
	            product.setProd_price(rs.getString("prod_price"));
	            product.setProd_discount(rs.getString("prod_discount"));
	            product.setProd_quantity(rs.getString("prod_quantity"));
	            product.setProd_imageName(rs.getString("prod_imageName"));
	            product.setCid(rs.getInt("cid"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return product;
	}
	@Override
	public List<Product> getlist() {
		Connection con = ConnectionProvider.getconnection();
		Product product=null;
		  List<Product> listProduct = new ArrayList<>();
		  Statement stmt;
		try {
			stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT * FROM product_operation");
	         
			while(rs.next()) 
			{
				    product = new Product();
				    product.setId(rs.getInt("prod_id"));
		            product.setProd_name(rs.getString("prod_name"));
		            product.setProd_description(rs.getString("prod_description"));
		            product.setProd_price(rs.getString("prod_price"));
		            product.setProd_discount(rs.getString("prod_discount"));
		            product.setProd_quantity(rs.getString("prod_quantity"));
		            product.setProd_imageName(rs.getString("prod_imageName"));
		            product.setCid(rs.getInt("cid"));
		            listProduct.add(product);
		           
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
         
		return listProduct;
	 }
		@Override
	public List<Cart> getCartProducts(ArrayList<Cart> cartlist) {
		
		List<Cart> products=new ArrayList<Cart>();
		Connection con = ConnectionProvider.getconnection();
		   
		try {
			if(cartlist.size()>0) {
				for(Cart item:cartlist) {
					String query="SELECT * FROM product_operation where prod_id=?";
					PreparedStatement psmt=con.prepareStatement(query);
					psmt.setInt(1, item.getId());
					ResultSet rs = psmt.executeQuery();
					
					while(rs.next()) {
						
						Product product =new Product();
						product.setProd_price(rs.getString("prod_price"));
						product.setProd_discount(rs.getString("prod_discount"));
						System.out.println();
						Cart row = new Cart();
						row.setId(rs.getInt("prod_id"));
						row.setProd_name(rs.getString("prod_name"));
						row.setProd_imageName(rs.getString("prod_imageName"));
						int calculated_price = product.getPriceAfterDiscount();
						System.out.println(calculated_price);
						System.out.println(item.getQuantity());
						row.setcalculated_price(calculated_price * item.getQuantity());
						products.add(row);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return products;
		
	}
	
	public int getTotalCartPrice(ArrayList<Cart> cartlist)
	{
		int sum=0;
		String query=null;
		PreparedStatement psmt = null;
		ResultSet rs =null;
		
		try {
			if(cartlist.size()>0) {
				for(Cart item:cartlist)
				{
					 query="select prod_price,prod_discount from product_operation where prod_id=?";
					psmt=this.con.prepareStatement(query);
					psmt.setInt(1, item.getId());
					rs=psmt.executeQuery();
					
					while(rs.next())
					{
						int price=Integer.parseInt(rs.getString("prod_price"));
						int discount = Integer.parseInt(rs.getString("prod_discount"));
						int d=(int)((discount/100.0)*price);
						sum= sum+(price-d)*item.getQuantity();
					}
					}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sum;
		}
	
	
	 //get all blogs ----------------
	
	
	public List<Blog> getAllBlogs()
		{
			List<Blog> bloglist = new ArrayList<Blog>();
			
	Connection con = ConnectionProvider.getconnection();
	try {
		Statement statement = con.createStatement();
		ResultSet resultset =null;
		resultset =statement.executeQuery(select_blog);
		//prod_id, prod_name, prod_description, prod_price, prod_discount, prod_quantity, prod_imageName
		while(resultset.next())
		{
			Blog blog = new Blog();
			blog.setB_id(resultset.getInt("B_id"));
			blog.setBlog_description(resultset.getString("Blog_description"));
			blog.setBlog_name(resultset.getString("BlogPhoto_Name"));
			blog.setBlog_title(resultset.getString("Blog_Title"));
			bloglist.add(blog);
		}
	} 
	catch (SQLException e) {
		e.printStackTrace();
	}
		return bloglist;
		}
	
	
	
	 public List <Blog> getBlogsDetails(int id)
		{
			List<Blog> list = new ArrayList<Blog>();
				        Connection con = ConnectionProvider.getconnection();
	       
	try {
		Statement statement = con.createStatement();
		ResultSet resultset =null;
		String detail_blog ="SELECT * FROM myblogs where B_id= "+id;
		resultset =statement.executeQuery(detail_blog);
		//prod_id, prod_name, prod_description, prod_price, prod_discount, prod_quantity, prod_imageName
		while(resultset.next())
		{
			Blog b = new Blog();
			b.setB_id(resultset.getInt("B_id"));
			b.setBlog_description(resultset.getString("Blog_description"));
			b.setBlog_name(resultset.getString("BlogPhoto_Name"));
			b.setBlog_title(resultset.getString("Blog_Title"));
			list.add(b);
		
		}
	} 
	catch (SQLException e) {
		e.printStackTrace();
	}
		return list;
			
	}

	
  }
