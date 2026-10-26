<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$account = $object->{'account'};
$ID = $object->{'ID'};
$StarNum = $object->{'StarNum'};

if(isset($account) || isset($ID) || isset($StarNum)){
	require_once __DIR__ . '/conn.php';

	connect();

	$result = mysqli_query($con, "UPDATE comment SET StarNum = '$StarNum' WHERE Account = '$account' and ID = '$ID'");
}

?>