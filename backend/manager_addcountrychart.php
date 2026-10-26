<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$country = $object->{'country'};
if(isset($country)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "INSERT INTO country_chart(country) VALUES('$country')");
}

?>