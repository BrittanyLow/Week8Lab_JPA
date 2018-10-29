<%-- 
    Document   : notes
    Created on : Oct 24, 2018, 11:13:01 AM
    Author     : 743953
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notes</title>
    </head>
    <body>
        <h1>Notes!</h1>
        <form method="post" action="notes?action=add">
            <textarea cols="40" rows="15" name="content">
            
            </textarea>
            <input type="submit" value="Add"/>
        </form>
        ${message}
    </body>
</html>
