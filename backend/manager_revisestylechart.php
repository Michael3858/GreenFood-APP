<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$style1 = $object->{'style1'};
$style2 = $object->{'style2'};

if(isset($style1) || isset($style2)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "UPDATE style_chart SET style = '$style2' WHERE style = '$style1'");
}

?>