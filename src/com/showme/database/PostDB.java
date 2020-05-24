package com.showme.database;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Component;

import com.showme.beans.Comment;
import com.showme.beans.Like;
import com.showme.beans.Post;

@Component
public class PostDB extends SocializeDB{

	private String hostingPassword = "AAAqtf44583";
	private String hostingUrl = "jdbc:mysql://node22899-socialize.ams1.jls.docktera.net/";
	
	private String localPassword = "";
	private String localUrl = "jdbc:mysql://localhost:3306/";

	//AAAqtf44583
	//node22899-socialize.ams1.jls.docktera.net
	//localhost:3306
	private String username = "root";
	private String password = localPassword;
	private String dbName = "whatsapp";
	private String url = localUrl + dbName;
	private String driver = "com.mysql.jdbc.Driver";
	private Connection con;


	private void dbClose() throws SQLException {
		con.close();
	}

	private void dbConnect() throws ClassNotFoundException, SQLException {
		// Step 2: load the driver
		Class.forName(driver);
		// Step 3: Make the connection
		con = DriverManager.getConnection(url, username, password);

	}
	
	public List<Post> getHomePosts(Integer userId) throws ClassNotFoundException, SQLException, IOException {

		List<Post> posts = new ArrayList<Post>();

		dbConnect();
		String sql = "SELECT * FROM post WHERE user_id IN ( SELECT contact_id FROM contact WHERE user_id = ? ) OR user_id = ? ORDER BY date DESC , time DESC";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1 , userId);
		pstmt.setInt(2, userId);
		
		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {
			Post post = new Post();
			post.setId(rst.getInt("id"));
			post.setUserId(rst.getInt("user_id"));

			Blob blob = rst.getBlob("post");

			InputStream inputStream = blob.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);

			post.setPost(base64Image);
			post.setCaption(rst.getString("caption"));
			post.setDate(rst.getDate("date"));
			post.setTime(rst.getTime("time"));

			post.setLikes(getLikeByPostId(post.getId()));

			post.setComments(getAllCommentsByPostId(post.getId()));

			posts.add(post);
		}

		dbClose();

		return posts;
	}
	
	private List<Like> getLikeByPostId(Integer post_id) throws ClassNotFoundException, SQLException {

		List<Like> likes = new ArrayList<Like>();

		String sql = "SELECT * FROM likes WHERE post_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, post_id);

		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {

			Like like = new Like();
			like.setId(rst.getInt("id"));
			like.setPostId(rst.getInt("post_id"));
			like.setUserId(rst.getInt("user_id"));

			likes.add(like);
		}

		return likes;
	}
	
	private List<Comment> getAllCommentsByPostId(Integer post_id) throws SQLException, ClassNotFoundException {

		List<Comment> comments = new ArrayList<Comment>();

		String sql = "SELECT * FROM comments WHERE post_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, post_id);

		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {

			Comment comment = new Comment();
			comment.setId(rst.getInt("id"));
			comment.setUserId(rst.getInt("user_id"));
			comment.setPostId(rst.getInt("post_id"));
			comment.setComment(rst.getString("comment"));
			comment.setDate(rst.getDate("date"));
			comment.setTime(rst.getTime("time"));

			comments.add(comment);

		}

		return comments;
	}

	public List<Post> getUserPosts(Integer userId) throws ClassNotFoundException, SQLException, IOException {
		
		List<Post> posts = new ArrayList<Post>();

		dbConnect();
		String sql = "SELECT * FROM post WHERE user_id = ? ORDER BY date DESC , time DESC";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1 , userId);
		
		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {
			Post post = new Post();
			post.setId(rst.getInt("id"));
			post.setUserId(rst.getInt("user_id"));

			Blob blob = rst.getBlob("post");

			InputStream inputStream = blob.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);

			post.setPost(base64Image);
			post.setCaption(rst.getString("caption"));
			post.setDate(rst.getDate("date"));
			post.setTime(rst.getTime("time"));

			post.setLikes(getLikeByPostId(post.getId()));

			post.setComments(getAllCommentsByPostId(post.getId()));

			posts.add(post);
		}

		dbClose();

		return posts;
	}

	
	public Integer likePost(Integer userId, Integer postId) throws ClassNotFoundException, SQLException {

		dbConnect();

		String sql = "INSERT INTO likes(user_id, post_id) VALUES(?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, postId);

		pstmt.execute();
		
		String query = "select id from likes where user_id=? and post_id = ?";
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, postId);
		
		ResultSet rst = pstmt.executeQuery();

		Integer likeId = 0;
		while(rst.next()) {
			likeId = rst.getInt("id");
		}
		
		dbClose();
		
		return likeId;
	}
	
	public void dislikePost(Integer userId, Integer postId) throws ClassNotFoundException, SQLException {
		dbConnect();

		String sql = "DELETE FROM likes WHERE user_id=? AND post_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, postId);

		pstmt.executeUpdate();

		dbClose();
	}

	public Like getLikeInformation(Integer likeId) throws ClassNotFoundException, SQLException {
		
		dbConnect();
		
		String sql = "SELECT * FROM likes WHERE id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, likeId);

		ResultSet rst = pstmt.executeQuery();

		Like like = new Like();
		
		while (rst.next()) {
			like.setId(rst.getInt("id"));
			like.setPostId(rst.getInt("post_id"));
			like.setUserId(rst.getInt("user_id"));
		}
		dbClose();
		
		return like;	
	}

	public Comment getCommentInformation(Integer commentId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		dbConnect();
		String sql = "SELECT * FROM comments WHERE id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, commentId);

		ResultSet rst = pstmt.executeQuery();
		Comment comment = new Comment();
		while (rst.next()) {

			comment.setId(rst.getInt("id"));
			comment.setUserId(rst.getInt("user_id"));
			comment.setPostId(rst.getInt("post_id"));
			comment.setComment(rst.getString("comment"));
			comment.setDate(rst.getDate("date"));
			comment.setTime(rst.getTime("time"));

		}

		dbClose();
		return comment;
	}

	public Integer getUserIdFromPostId(Integer postId) throws ClassNotFoundException, SQLException {
		dbConnect();
		String sql = "SELECT user_id FROM post WHERE id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, postId);

		ResultSet rst = pstmt.executeQuery();

		Integer userId = 0;
		while (rst.next()) {
			
			userId = rst.getInt("user_id");
		}
		
		dbConnect();
		return userId;
	}
	
	public Integer commentPost(Comment comment) throws ClassNotFoundException, SQLException {

		dbConnect();

		String sql = "INSERT INTO comments(user_id, post_id, comment, date, time) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, comment.getUserId());
		pstmt.setInt(2, comment.getPostId());
		pstmt.setString(3, comment.getComment());
		pstmt.setDate(4, comment.getDate());
		pstmt.setTime(5, comment.getTime());

		pstmt.execute();

		Integer commentId = 0;
		
		String query = "select id from comments where user_id=? and post_id = ?";
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, comment.getUserId());
		pstmt.setInt(2, comment.getPostId());
		
		ResultSet rst = pstmt.executeQuery();

		
		while(rst.next()) {
			commentId = rst.getInt("id");
		}
		
		dbClose();
		
		return commentId;
		

	}

	public Post getPostWithId(Integer postId) throws ClassNotFoundException, SQLException, IOException {
		
		dbConnect();
		String sql = "SELECT * FROM post WHERE id = ?";
		Post post = new Post();
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1 , postId);
		
		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {
			
			post.setId(rst.getInt("id"));
			post.setUserId(rst.getInt("user_id"));

			Blob blob = rst.getBlob("post");

			InputStream inputStream = blob.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);

			post.setPost(base64Image);
			post.setCaption(rst.getString("caption"));
			post.setDate(rst.getDate("date"));
			post.setTime(rst.getTime("time"));

			post.setLikes(getLikeByPostId(post.getId()));

			post.setComments(getAllCommentsByPostId(post.getId()));

		}

		dbClose();
		
		return post;
	}

	public List<Post> getRandomPicturesOfUserWhichAreNotFriend(Integer userId) throws ClassNotFoundException, SQLException, IOException {
		List<Post> posts = new ArrayList<Post>();

		dbConnect();
		String sql = "select * from post where user_id not in ( select contact_id from contact where user_id = ? OR contact_id = ? ) order by rand() DESC, rand() DESC";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1 , userId);
		pstmt.setInt(2 , userId);
		
		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {
			
			int present = 0;
			
				for (Post p : posts) {
					if(p.getUserId() == rst.getInt("user_id"))
						present = 1;	
				}
				
				if( present == 0 && ( posts.size() < 9 )) {
					
					Post post = new Post();
					post.setId(rst.getInt("id"));
					post.setUserId(rst.getInt("user_id"));

					Blob blob = rst.getBlob("post");

					InputStream inputStream = blob.getBinaryStream();
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[4096];
					int bytesRead = -1;

					while ((bytesRead = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, bytesRead);
					}

					byte[] imageBytes = outputStream.toByteArray();
					String base64Image = Base64.getEncoder().encodeToString(imageBytes);

					post.setPost(base64Image);
					post.setCaption(rst.getString("caption"));
					post.setDate(rst.getDate("date"));
					post.setTime(rst.getTime("time"));

					post.setLikes(getLikeByPostId(post.getId()));

					post.setComments(getAllCommentsByPostId(post.getId()));

					posts.add(post);
				}
			}

		dbClose();

		return posts;
	}

	public void deleteLike(Integer postId) throws ClassNotFoundException, SQLException {
		dbConnect();
		String sql = "DELETE FROM likes WHERE post_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, postId);
		pstmt.executeUpdate();
		dbClose();
	}

	public void deletePost(Integer postId) throws SQLException, ClassNotFoundException {
		deleteLike(postId);
		deleteComment(postId);
		dbConnect();
		String sql = "DELETE FROM post WHERE id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, postId);
		pstmt.executeUpdate();
		dbClose();
	}
	
	public void insertComment(Comment comment) throws ClassNotFoundException, SQLException {
		dbConnect();
		String sql = "INSERT INTO comments(user_id, post_id, comment, date, time) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, comment.getUserId());
		pstmt.setInt(2, comment.getPostId());
		pstmt.setString(3, comment.getComment());
		pstmt.setDate(4, comment.getDate());
		pstmt.setTime(5, comment.getTime());
		pstmt.execute();
		dbClose();
	}

	public void deleteComment(Integer postId) throws ClassNotFoundException, SQLException {
		dbConnect();
		String sql = "DELETE FROM comments WHERE post_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, postId);
		pstmt.executeUpdate();
		dbClose();
	}

	public void insertPost(Integer userId, String path) throws ClassNotFoundException, SQLException, FileNotFoundException {
		dbConnect();

		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		Time time = new Time(millis);
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);

		String sql = "INSERT INTO post (user_id, post, caption, date, time)" + " VALUES(?, ?, ?, ?, ?)";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		pstmt.setBinaryStream(2, fis, (int) file.length());
		pstmt.setString(3, path);
		pstmt.setDate(4, date);
		pstmt.setTime(5, time);

		pstmt.execute();

		dbClose();

	}

	public Post getLatestPost(Integer userId) throws ClassNotFoundException, SQLException, IOException {
		
		Post post = new Post();
		
		dbConnect();
		String sql = "SELECT * FROM post WHERE user_id = ? ORDER BY date DESC , time DESC limit 1";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1 , userId);
		
		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {
			post.setId(rst.getInt("id"));
			post.setUserId(rst.getInt("user_id"));

			Blob blob = rst.getBlob("post");

			InputStream inputStream = blob.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);

			post.setPost(base64Image);
			post.setCaption(rst.getString("caption"));
			post.setDate(rst.getDate("date"));
			post.setTime(rst.getTime("time"));

			post.setLikes(getLikeByPostId(post.getId()));

			post.setComments(getAllCommentsByPostId(post.getId()));

		}

		dbClose();

		return post;
	}

	public List<Post> morePhotosFromUserOfPost(Integer userId, Integer postId) throws ClassNotFoundException, SQLException, IOException {
		List<Post> posts = new ArrayList<Post>();

		dbConnect();
		String sql = "SELECT * FROM post WHERE user_id = ? and id != ? ORDER BY date DESC , time DESC limit 6";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1 , userId);
		pstmt.setInt(2 , postId);
		
		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {
			Post post = new Post();
			post.setId(rst.getInt("id"));
			post.setUserId(rst.getInt("user_id"));

			Blob blob = rst.getBlob("post");

			InputStream inputStream = blob.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);

			post.setPost(base64Image);
			post.setCaption(rst.getString("caption"));
			post.setDate(rst.getDate("date"));
			post.setTime(rst.getTime("time"));

			post.setLikes(getLikeByPostId(post.getId()));

			post.setComments(getAllCommentsByPostId(post.getId()));

			posts.add(post);
		}

		dbClose();

		return posts;
	}

	
}
