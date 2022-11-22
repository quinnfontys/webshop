const Navbar = () => {
    return (
        <div>
            <nav class="navbar navbar-expand-lg bg-light">
                <div class="container-fluid">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="home">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="form">Form test</a>
                            </li>
                        </ul>
                    </div>

                </div>
            </nav>
        </div>
    );
};

export default Navbar;