<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<label for = "question">Question: 
	</label><textarea rows="2" cols="50" id="text1" name="question"></textarea>
	<br><br>
	<label for="option1">option1:
	</label><input type = "text" name="option1" id="text2" >
   <br><br>
   <label for="option2">option2:
	</label><input type = "text" name="option2" id="text3" >
    <br><br>
    <label for="option3">option3:
	</label><input type = "text" name="option3" id="text4" >
    <br><br>
    <label for="option4">option4:
	</label><input type = "text" name="option4" id="text5" >
    <br><br>
    <label for="ans">Ans:</label> 
	<input type="radio" name="ans" id="ans1" value="1" checked="checked">1
	<input type="radio" name="ans" id="ans2" value="2">2
	<input type="radio" name="ans" id="ans3" value="3">4
	<input type="radio" name="ans" id="ans4" value="4">4
	<br><br>
	<label for="TestID">TestID:</label>
	<input type="text" name="TestID" value="<%=request.getParameter("TestID")%>" readonly>
    <label for="submit"></label>
   <input type="submit" value="add"  />
   <input type="hidden" name="page" value="questioninsertion.jsp"/>
   
</form>
<script type="text/javascript">
function validate()
{
    if(document.getElementById("text1").value=="" ){
        alert( "please enter Question" );
        return false;
    }
    else if(document.getElementById("text2").value=="" ||document.getElementById("text3").value==""|| document.getElementById("text5").value==""||document.getElementById("text6").value=="" ){
		alert("please enter all options");
		return false;
	}
    else{
    	return true;
    }
   
}
</script>


</body>
</html>