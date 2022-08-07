<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title >Online Shopping Cart Email Verification</title>
</head>

<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center" valign="top" bgcolor="#838383"
				style="background-color: coral;"><br> <br>
				<table width="800" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="center" valign="top" bgcolor="#d3be6c"
							style="background-color: HoneyDew; font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #000000; padding: 0px 15px 10px 15px;">
							
							<div style="font-size: 48px; color:navy;">
								<b>Online Shopping Cart</b>
							</div>
							
							<div style="font-size: 32px; color: crimson;">
								<br> Dear ${merchant.getName()} <br><br>
							</div>
							<div style="font-size: 24px; color: blue;">
								<br> Click below link to verify your account creation...<br> 
								<h3><a style="font-size: 24px; color: blue;" href="http://localhost:8080/merchants/save/${merchant.getToken()}">Verify my account</a></h3>
								<br><h3 style="font-size: 32px; color: crimson;">Thank you..</h3>
								  <b><h2 style="font-size: 24px; color: black;">Online Shopping Cart Team</h2></b>
							</div>
						</td>
					</tr>
				</table> <br> <br></td>
		</tr>
	</table>
</body>
</html>