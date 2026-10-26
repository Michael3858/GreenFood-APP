<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$account = $object->{'account'};
$password = $object->{'password'};

if(isset($account) || isset($password)){
	require_once __DIR__ . '/conn.php';

	connect();

	$result = mysqli_query($con, "UPDATE person SET password = '$password' WHERE account = '$account'");
}

?>