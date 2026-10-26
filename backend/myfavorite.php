<?php
require_once __DIR__ . '/conn.php';

connect();

$Account = $_POST["Account"];
$datas = array();
$mysql_qry = "select restaurant.ID, Name from restaurant, myfavorite where restaurant.ID = myfavorite.ID and Account = '$Account';";
$result = mysqli_query($con, $mysql_qry);
while ($row = mysqli_fetch_assoc($result)) {
       $datas[] = $row;
}	

print(json_encode($datas,JSON_UNESCAPED_UNICODE));
close();

?>