<?php
require_once __DIR__ . '/conn.php';

connect();

$user_acc = $_POST["account"];
$user_pass = $_POST["password"];
$datas = array();
$mysql_qry = "select * from person where account = '$user_acc' and password = '$user_pass';";
$result = mysqli_query($con, $mysql_qry);
while ($row = mysqli_fetch_assoc($result)) {
       $datas[] = $row;
}	

print(json_encode($datas,JSON_UNESCAPED_UNICODE));
close();

?>