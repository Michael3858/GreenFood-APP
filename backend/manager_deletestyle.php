<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$ID = $object->{'ID'};
$style = $object->{'style'};
if(isset($ID) || isset($style)){
 require_once __DIR__ . '/conn.php';

 connect();

 $result = mysqli_query($con, "DELETE FROM style WHERE ID = '$ID' and style = '$style'");
}

?>