<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$account = $object->{'account'};
$ID = $object->{'ID'};
$Time = $object->{'Time'};

if(isset($account) || isset($ID) || isset($Time)){
	require_once __DIR__ . '/conn.php';

	connect();

	$result = mysqli_query($con, "INSERT INTO browsing_history(Account, ID, Time) VALUES('$account', '$ID', '$Time')");
}

?>