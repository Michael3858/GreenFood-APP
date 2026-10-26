<?php
require_once __DIR__ . '/conn.php';

connect();

$ID = $_POST["ID"];
$Account = $_POST["Account"];
$datas = array();
$mysql_qry = "select * from myfavorite where ID = '$ID' and Account = '$Account';";
$result = mysqli_query($con, $mysql_qry);
if(mysqli_num_rows($result) > 0){
	echo "yes";
}
else{
	echo "no";
}
close();

?>