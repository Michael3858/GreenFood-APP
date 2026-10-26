<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$country1 = $object->{'country1'};
$country2 = $object->{'country2'};

if(isset($country1) || isset($country2)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "UPDATE country_chart SET country = '$country2' WHERE country = '$country1'");
}

?>