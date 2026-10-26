<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$payment1 = $object->{'payment1'};
$payment2 = $object->{'payment2'};

if(isset($payment1) || isset($payment2)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "UPDATE payment_chart SET payment = '$payment2' WHERE payment = '$payment1'");
}

?>