<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$ID = $object->{'ID'};
$food = $object->{'food'};
$country = $object->{'country'};
if(isset($ID) || isset($food) || isset($country)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "DELETE FROM food WHERE ID = '$ID' and food = '$food' and country = '$country'");
}

?>