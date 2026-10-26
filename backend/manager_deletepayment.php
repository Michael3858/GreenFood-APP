<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$ID = $object->{'ID'};
$payment = $object->{'payment'};
if(isset($ID) || isset($payment)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "DELETE FROM payment WHERE ID = '$ID' and payment = '$payment'");
}

?>