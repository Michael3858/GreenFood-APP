<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$country = $object->{'country'};
if(isset($country)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "DELETE FROM country_chart WHERE country = '$country'");
}

?>