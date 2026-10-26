<?php
require_once __DIR__ . '/conn.php';

connect();

$datas = array();
$mysql_qry = "SELECT ID FROM restaurant WHERE Length(ID) = 3 or Length(ID) = 4 ORDER BY ID DESC LIMIT 1;";
$result = mysqli_query($con, $mysql_qry);
while ($row = mysqli_fetch_assoc($result)) {
       $datas[] = $row;
}	

print(json_encode($datas,JSON_UNESCAPED_UNICODE));
close();

?>