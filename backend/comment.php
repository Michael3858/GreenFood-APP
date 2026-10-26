<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$account = $object->{'account'};
$ID = $object->{'ID'};
$comment = $object->{'comment'};
$time = $object->{'time'};

if(isset($account) || isset($ID) || isset($comment) || isset($time)){
	require_once __DIR__ . '/conn.php';

	connect();

	$result = mysqli_query($con, "INSERT INTO review(account, ID, comment, time) VALUES('$account', '$ID', '$comment', '$time')");
}

?>