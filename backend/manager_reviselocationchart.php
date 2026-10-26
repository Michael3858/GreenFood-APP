<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$city = $object->{'city'};
$district1 = $object->{'district1'};
$district2 = $object->{'district2'};

if(isset($city) || isset($district1) || isset($district2)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "UPDATE location_chart SET district = '$district2' WHERE city = '$city' and district = '$district1'");
}

?>