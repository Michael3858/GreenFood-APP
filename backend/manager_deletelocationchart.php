<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$city = $object->{'city'};
$district = $object->{'district'};
if(isset($city) || isset($district)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "DELETE FROM location_chart WHERE city = '$city' AND district = '$district'");
}

?>