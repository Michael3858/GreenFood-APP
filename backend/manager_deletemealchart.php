<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$meal = $object->{'meal'};
if(isset($meal)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "DELETE FROM meal_chart WHERE meal = '$meal'");
}

?>