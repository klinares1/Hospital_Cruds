<?php
include('./conexion.php');
$link=conectar();
//traemos los datos capturados en android studio
$user=$_REQUEST['user'];
$pass=$_REQUEST['pass'];


$sql1="select Nombre_Admin from administrador where CC_Administrador = "+$user;
$res1=mysqli_query($link,$sql1) or die("Error en la consulta $sql1".mysqli_error($link));

$sql2="select Nombre_Paciente from paciente where CC_Paciente = "+$user;
$res2=mysqli_query($link,$sql2) or die("Error en la consulta $sql2".mysqli_error($link));

$sql3="select Nombre_Medico from medico where CC_Medico = "+$user;
$res3=mysqli_query($link,$sql3) or die("Error en la consulta $sql3".mysqli_error($link));

$sql4="select Nombre_Admin from administrador where Contraseña_Admin = "+$pass;
$res4=mysqli_query($link,$sql4) or die("Error en la consulta $sql4".mysqli_error($link));

$sql5="select Nombre_Paciente from paciente where Contraseña = "+$pass;
$res5=mysqli_query($link,$sql5) or die("Error en la consulta $sql5".mysqli_error($link));

$sql6="select Nombre_Medico from medico where Contraseña = "+$pass;
$res6=mysqli_query($link,$sql6) or die("Error en la consulta $sql6".mysqli_error($link));

if($res1) {
    if($res4){
        echo "Bienvenido_Administrador";
    }
}else{
    if($res2) {
        if($res5){
            echo "Bienvenido_Paciete";
        }
    }
    else{
        if($res3) {
            if($res6){
                echo "Bienvenido_Medico";
            }
        }
        else{
            echo "ERROR: Usuario o Contraseña erroneos...";
        }
    }
}
//cierro la conexion;
mysqli_close($link);
?>