<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$city1 = $object->{'city1'};
$city2 = $object->{'city2'};

if(isset($city1) || isset($city2)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "UPDATE city_chart SET city = '$city2' WHERE city = '$city1'");
}

?>