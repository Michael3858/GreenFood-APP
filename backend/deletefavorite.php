<?php
$result = file_get_contents('php://input');
$object = json_decode($result);
$account = $object->{'account'};
$ID = $object->{'ID'};

if(isset($account) || isset($ID)){
	require_once __DIR__ . '/conn.php';

	connect();

	$result = mysqli_query($con, "DELETE FROM myfavorite WHERE Account = '$account' AND ID = '$ID'");
}

?>