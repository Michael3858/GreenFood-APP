<?php
require_once __DIR__ . '/conn.php';

connect();

$datas = array();
$mysql_qry = "select restaurant.ID, Name, count(restaurant.ID) as Num, AVG(StarNum) as AvgStar from restaurant, comment where (restaurant.ID = comment.ID) group by restaurant.ID having (AVG(StarNum) >= 4.0 and count(restaurant.ID) >= 5) order by AVG(StarNum) desc;";
$result = mysqli_query($con, $mysql_qry);
while ($row = mysqli_fetch_assoc($result)) {
       $datas[] = $row;
}	

print(json_encode($datas,JSON_UNESCAPED_UNICODE));
close();

?>