<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$style = $object->{'style'};
if(isset($style)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "DELETE FROM style_chart WHERE style = '$style'");
}

?>