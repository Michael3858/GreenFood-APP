<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$food = $object->{'food'};
if(isset($food)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "DELETE FROM food_chart WHERE food = '$food'");
}

?>