<#import "macros/navbar.ftl" as nav>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates|Comfortaa|Quicksand|IBM+Plex+Sans"
          rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
    <link href="/CSS/bankassistant.css" rel="stylesheet">
    <link href="/CSS/accordion.css" rel="stylesheet">
    <script src="/JavaScript/accordion.js"></script>


    <script src="/JavaScript/chandgeColor.js" rel="stylesheet"></script>
    <title>Money assistance</title>

</head>
<body>


<div class="demo-layout-transparent mdl-layout mdl-js-layout"/>
<@nav.navbar></@nav.navbar>

<h6 style="position: absolute;top: 80px; right: 10%">
    <a href="/home?thema=black" onclick="chandgeColor()">Dark</a>|<a href="/home?thema=standart">Standart</a></a>
</h6>

<div class="main">
    <div>
        <div class="panel">
            <div class="panel-heading">
                <a>
                    <span>Lorem ipsum dolor</span>
                </a>
            </div>
            <div class="panel-collapse">
                <div class="panel-body">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet animi architecto consequatur culpa
                        cupiditate debitis delectus ex fugiat in ipsum iure, nemo, neque nobis placeat provident quas
                        quidem ratione reiciendis repellat reprehenderit repudiandae rerum sequi ullam velit voluptatum?
                        Neque, quos!?</p>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate doloribus dolorum ea maxime
                        minima optio perspiciatis quaerat repellendus rerum ullam?</p>
                    <h5>Lorem ipsum dolor sit <strong>«amet»: </strong></h5>
                    <div class="wrapper-ul">
                        <ul>
                            <li>Lorem ipsum:</li>
                            <li><a href="#">Velit voluptatum</a></li>
                            <li><a href="#">Cupiditate debitis</a></li>
                            <li><a href="#">Debitis delectus</a></li>
                            <li><a href="#">Paceat provident</a></li>
                        </ul>
                    </div>
                    <div class="button"><a href="#">Extended equipment</a></div>
                </div>
            </div>
        </div>
        <div class="panel">
            <div class="panel-heading">
                <a>
                    <span>Full functional set of CMS</span>
                </a>
            </div>
            <div class="panel-collapse">
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consequatur delectus dolorum eius eos
                    impedit ipsa ipsum labore minus, molestiae nemo nesciunt nulla, optio praesentium quod reprehenderit
                    sit sunt tenetur voluptas:</p>
                <div class="wrapper-ul df">
                    <ul>
                        <li>Lorem ipsumdolor:</li>
                        <li><a href="#">Lorem ipsum</a></li>
                        <li><a href="#">Repellat reprehenderit</a></li>
                        <li><a href="#">Quidem ratione</a></li>
                        <li><a href="#">Velit voluptatum</a></li>
                        <li><a href="#">Debitis delectus</a></li>
                    </ul>
                </div>
                <div class="button"><a href="#">Additional modules</a></div>

            </div>
        </div>
        <div class="panel">
            <div class="panel-heading">
                <a href="#">
                    <span>Several basic advantages</span>
                </a>
            </div>
            <div class="panel-collapse">
                <div class="panel-body">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab amet cum, debitis fugiat molestiae
                        nisi, placeat provident quos ratione sunt tempore totam ullam velit. Consequuntur ex incidunt
                        labore quae quos tempora, voluptatibus? Autem culpa dicta dolorum eaque error eveniet nam odio
                        suscipit tenetur, voluptates! Ad atque maiores officiis quasi sequi!</p>
                    <div class="button"><a href="#">Подробнее о преимуществах</a></div>

                </div>
            </div>
        </div>
    </div>
</div>

<div class="card-list" style="width: 90%; margin-left: 5%; margin-top:2%; margin-bottom: 5%">
    <div class="demo-card-square mdl-card mdl-shadow--2dp" style="width: 350px; height: 500px">
        <img style="width: 350px; height: 300px"
             src="https://freefrontend.com/assets/img/css-carousels/pure-css-carousel.png">
        <div class="mdl-card__title mdl-card--expand">
            <h2 class="mdl-card__title-text">Salary</h2>
        </div>
        <div class="mdl-card__supporting-text">
            Add salaries to automatically increase your finances.
        </div>
    </div>
    <div class="demo-card-square mdl-card mdl-shadow--2dp" style="width: 350px; height: 500px">
        <img style="width: 350px; height: 300px"
             src="http://www.financedais.com/wp-content/uploads/2016/04/control-your-finances.jpg">
        <div class="mdl-card__title mdl-card--expand">
            <h2 class="mdl-card__title-text">Control</h2>
        </div>
        <div class="mdl-card__supporting-text">
            Сontrol your finances
        </div>
        <div class="mdl-card__actions mdl-card--border">
        <#if (user)??>
            <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" href="/signUp">
                Sign Up
            </a>
        </#if>
        </div>
    </div>
    <div class="demo-card-square mdl-card mdl-shadow--2dp" style="width: 350px; height: 500px">
        <img style="width: 350px; height: 300px" src="/resources/circle.png">
        <div class="mdl-card__title mdl-card--expand">
            <h2 class="mdl-card__title-text">Check your statistik</h2>
        </div>
        <div class="mdl-card__supporting-text">
            Pie chart clearly shows your expenses.
        </div>
        <div class="mdl-card__actions mdl-card--border">
            <#if (user)??>
                <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" href="/signUp">
                    Sign Up
                </a>
            </#if>
        </div>
    </div>
</div>


</body>
</html>
