<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$meal1 = $object->{'meal1'};
$meal2 = $object->{'meal2'};

if(isset($meal1) || isset($meal2)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "UPDATE meal_chart SET meal = '$meal2' WHERE meal = '$meal1'");
}

?>