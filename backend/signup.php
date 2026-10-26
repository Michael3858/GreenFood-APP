<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$account = $object->{'account'};
$password = $object->{'password'};
$name = $object->{'name'};
$date = $object->{'date'};
$gender = $object->{'gender'};

if(isset($account) || isset($password) || isset($name) || isset($date) || isset($gender)){
	require_once __DIR__ . '/conn.php';

	connect();

	$result = mysqli_query($con, "INSERT INTO person(account, password, name, date, gender) VALUES('$account', '$password', '$name', '$date', '$gender')");
}

?>