<%@ page import="com.example.lostandfound.model.User" %>
<%
  User user = (User) request.getAttribute("user");
  boolean isEdit = user != null;
%>

<label>First Name:</label>
<label>
  <input type="text" name="firstName" value="<%= isEdit ? user.getFirstName() : "" %>" required>
</label><br>

<label>Last Name:</label>
<label>
  <input type="text" name="lastName" value="<%= isEdit ? user.getLastName() : "" %>" required>
</label><br>

<label>Username:</label>
<label>
  <input type="text" name="username" value="<%= isEdit ? user.getUsername() : "" %>" required>
</label><br>

<label>Email:</label>
<label>
  <input type="email" name="email" value="<%= isEdit ? user.getEmail() : "" %>" required>
</label><br>

<label>Password:</label>
<label>
  <input type="password" name="password" value="<%= isEdit ? user.getPassword() : "" %>" required>
</label><br>

<label>Contact:</label>
<label>
  <input type="text" name="contact" value="<%= isEdit ? user.getContact() : "" %>">
</label><br>

<label>Address:</label>
<label>
  <input type="text" name="address" value="<%= isEdit ? user.getAddress() : "" %>">
</label><br>

<label>Role:</label>
<label>
  <input type="text" name="role" value="<%= isEdit ? user.getRole() : "" %>">
</label><br>

<label>Avatar Image URL:</label>
<label>
  <input type="text" name="avatarImage" value="<%= isEdit ? user.getAvatarImage() : "" %>">
</label><br>
