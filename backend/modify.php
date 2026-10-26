<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$account = $object->{'account'};
$email = $object->{'email'};
$phoneNum = $object->{'phoneNum'};

if(isset($account) || isset($email) || isset($phoneNum)){
	require_once __DIR__ . '/conn.php';

	connect();

	$result = mysqli_query($con, "UPDATE person SET email = '$email', phoneNum = '$phoneNum' WHERE account = '$account'");
}

?>