<?php
require_once __DIR__ . '/conn.php';

connect();

$City = $_POST["City"];
$District = $_POST["District"];
$datas = array();
$mysql_qry = "select * from restaurant where City = '$City' and District = '$District';";
$result = mysqli_query($con, $mysql_qry);
while ($row = mysqli_fetch_assoc($result)) {
       $datas[] = $row;
}	

print(json_encode($datas,JSON_UNESCAPED_UNICODE));
close();

?>