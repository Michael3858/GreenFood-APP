<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$food = $object->{'food'};
if(isset($food)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "INSERT INTO food_chart(food) VALUES('$food')");
}

?>