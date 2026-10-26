<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$city = $object->{'city'};
if(isset($city)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "INSERT INTO city_chart(city) VALUES('$city')");
}

?>