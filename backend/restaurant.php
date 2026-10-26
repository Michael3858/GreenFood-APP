<?php
require_once __DIR__ . '/conn.php';

connect();

$ID = $_POST["ID"];
$datas = array();
$mysql_qry = "select restaurant.Name, restaurant.BusinessHours, restaurant.Address, restaurant.Phone, restaurant.Price, restaurant.Meal, restaurant.Delivery, restaurant.WiFi, restaurant.Accessibility, restaurant.FanPage, restaurant.URL, restaurant.pictureURL, restaurant.City, restaurant.District, style.style, payment.payment, food.food, food.country, myfavorite.Account, review.time, review.account, review.comment, comment.Account as Cacc, comment.StarNum from restaurant left join style on restaurant.ID = style.ID left join payment on restaurant.ID = payment.ID left join food on restaurant.ID = food.ID left join myfavorite on restaurant.ID = myfavorite.ID left join review on restaurant.ID = review.ID left join comment on restaurant.ID = comment.ID where restaurant.ID = '$ID' order by time desc;";
$result = mysqli_query($con, $mysql_qry);
while ($row = mysqli_fetch_assoc($result)) {
       $datas[] = $row;
}	

print(json_encode($datas,JSON_UNESCAPED_UNICODE));
close();

?>