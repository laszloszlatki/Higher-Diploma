    <?php
    
     // credentials for localhost
     $dbhost = "localhost";
     $dbname = "chat";
     $dbuser = "root";
     $dbpass = "";

     // // credentials for hive server
     // $host = "hive.csis.ul.ie";
     // $username = "group07";
     // $password = "-zk_2@c!K)G{4Y/[";
     // $dbname = "dbgroup07";

     try{
          // estabilishing connection
          $db = new PDO("mysql:dbhost=$dbhost;dbname=$dbname", "$dbuser", "$dbpass");
     }catch(PDOException $e){
          echo $e -> getMessage();
     }
?>