<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$payment = $object->{'payment'};
if(isset($payment)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "DELETE FROM payment_chart WHERE payment = '$payment'");
}

?>