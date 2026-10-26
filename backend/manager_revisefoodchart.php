<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$food1 = $object->{'food1'};
$food2 = $object->{'food2'};

if(isset($food1) || isset($food2)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "UPDATE food_chart SET food = '$food2' WHERE food = '$food1'");
}

?>