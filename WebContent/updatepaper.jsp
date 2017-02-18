<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QuestionInsertion</title>
<style type= "text/css">
		label{ width:180px;display:inline-block; text-align:right;
			text-align:top ;color:green}
</style>
</head>
<body>
<form action="./QuestionBankUpdationServlet" method="post"  onsubmit="return validate();">
	<label for="TestID">TestID:</label>
	<input type="text" name="TestID" value="<%=request.getParameter("TestID")%>" readonly>
	<br><br>
	<label for = "question">QuestionNO: 
	</label><input type="text" name="QNO" id="text1" value=<%=request.getAttribute("QNO") %>>
	<input type="hidden" name="page" value="updatepaper.jsp"/>
	<input type="hidden" name="QID" value=<%=request.getAttribute("QID")%>>
	<br><br>
	  <label for="submit"></label>
   <input type="submit" name="act" value="click" >
	<label for = "question">Question: 
	</label><textarea rows="2" cols="50" id="text2" name="question" ><%=request.getAttribute("question") %></textarea>
	<br><br>
	<label for="option1">option1:
	</label><textarea rows="1" cols="50" id="text3" name="option1" ><%=request.getAttribute("option1") %></textarea>
   <br><br>
   <label for="option2">option2:
	</label><textarea rows="1" cols="50" id="text4" name="option2" ><%=request.getAttribute("option2") %></textarea>
    <br><br>
    <label for="option3">option3:
	</label><textarea rows="1" cols="50" id="text5" name="option3" ><%=request.getAttribute("option3") %></textarea>
    <br><br>
    <label for="option4">option4:
	</label><textarea rows="1" cols="50" id="text6" name="option4" ><%=request.getAttribute("option4") %></textarea>
    <br><br>
    <label for="ans">Ans:</label> 
	<input type = "text" name="ans" id="text7" value=<%=request.getAttribute("ans") %>>
    <br><br>
	<br><br>
    <label for="submit"></label>
   <input type="submit" name="act" value="update"/>
</form>
<script type="text/javascript">
function validate()
{
    if(document.getElementById("text1").value=="" &&  ){
        alert( "please enter QuestionNo." );
        return false;
    }
    else if(document.getElementById("text2").value=="" ||document.getElementById("text3").value==""|| document.getElementById("text5").value==""||document.getElementById("text6").value=="" ){
		alert("please enter all options");
		return false;
	}
    else if(document.getElementById("text7").value=="" ){
        alert( "please enter ans" );
        return false;
    }
    else{
    	return true;
    }
}
</script>


</body>
</html>