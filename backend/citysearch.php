<?php
require_once __DIR__ . '/conn.php';

connect();

$City = $_POST["City"];

$datas = array();
$mysql_qry = "select restaurant.ID, Name, District, style, payment, Meal from restaurant left join style on restaurant.ID = style.ID left join payment on restaurant.ID = payment.ID where City = '$City';";
$result = mysqli_query($con, $mysql_qry);
while ($row = mysqli_fetch_assoc($result)) {
       $datas[] = $row;
}	

print(json_encode($datas,JSON_UNESCAPED_UNICODE));
close();

?>