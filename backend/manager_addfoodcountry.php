<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$ID = $object->{'ID'};
$food = $object->{'food'};
$country = $object->{'country'};

if(isset($ID) || isset($food) || isset($country)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "INSERT INTO food(ID, food, country) VALUES('$ID', '$food', '$country')");
}

?>