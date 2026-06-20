<?php

$host = "localhost";
$user = "root";
$password = "";
$dbname = "software_facturacion";

$conn = new mysqli(
    $host,
    $user,
    $password,
    $dbname
);

if($conn->connect_error){
    die("Error de conexión");
}

?>