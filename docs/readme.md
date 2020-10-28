# CPASS BACK-END
Back-end component for CPass. Comprehensive with third-party integration components.

## Development
Each third-party integration MUST be split into a separate `/integ-<name>` module, to be able to swap them as required. The implementations MAY be either POJOs, CDIs or EJBs, given the complexity of the integration; the simplest type of implementation SHOULD be used at all times.\
Whenever a new integration is added, it MUST be added to the `/ear` and `/tar` modules to allow their usage.

The separation of concerns regarding the modules MUST be respected:
- `/lib` contains the libraries, to be used across multiple modules
- `/web` contains the REST entrypoints to the application
- `/ejb` contains the business logic
- `/integ-<name>` contains the logic for the integration to a third-party software
- `/ear` contains the packaging logic for an EAR archive
- `/tar` contains the packaging logic for an TAR archive, for automated distribution
- `/angular` contains the packaging for the front-end component in case the automation cannot deal with a full front-end distribution (it SHOULD contain the build output of the `cpassfe` project)

The REST endpoints are documented via [Swagger](https://swagger.io/specification/v2/) in YAML format. The YAML entrypoint is the `/docs/cpass.yml` file, and the definitions are split into partial files in the `/docs` subfolders.\
The YAML generation is manual and not mediated by the Swagger tools.

## Configuration
In case a new profile is to be added, it MUST be referenced in the `<profiles>` section of the `pom.xml`, and the corresponding `properties` file MUST be added in the `/profiles` folder.

### Properties configuration
- jpa.dataSource: the datasource JNDI name
- jpa.showSql: whether the SQL should be shown
- jpa.formatSql: whether the shown SQL should be formatted (only used if `jpa.showSql=true`)
- corsfilter.enableCors: whether to enable CORS usage
- authfilter.devMode: whether to apply the dev-mode to the authentication filter, effectively setting a custom user to the application
- authfilter.authAdapterName: the fully qualified name for the authentication adapter
- authfilter.iride.cookieName: the cookie name to check for IRIDE authentication
- xsrffilter.disabled: whether to disable the XSRF filter
- angularfilter.indexUrl: the URL page for the angular filter
- application.debug-mode: whether the application should be started in debug mode
- report.endpoint: the report engine entrypoint
- mail.smtp.auth: mail configurations (not yet used)
- mail.smtp.starttls.enable: mail configurations (not yet used)
- mail.smtp.host: mail configurations (not yet used)
- mail.smtp.port: mail configurations (not yet used)
- mail.username: mail configurations (not yet used)
- mail.password: mail configurations (not yet used)
- mail.from: mail configurations (not yet used)
- mail.from.name: mail configurations (not yet used)
