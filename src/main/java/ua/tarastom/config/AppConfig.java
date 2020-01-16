package ua.tarastom.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan("ua.tarastom")
@EnableTransactionManagement
@PropertySource({"classpath:persistence-mysql.properties"})
public class AppConfig implements WebMvcConfigurer {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private Environment environment;

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public DataSource getDataSource() {

        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
            dataSource.setDriverClass(environment.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        logger.info("jdbc.url=" + environment.getProperty("jdbc.url"));
        logger.info("jdbc.user=" + environment.getProperty("jdbc.user"));

        dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        dataSource.setUser(environment.getProperty("jdbc.user"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));

        dataSource.setInitialPoolSize(Integer.parseInt(environment.getProperty("connection.pool.initialPoolSize")));
        dataSource.setMaxIdleTime(Integer.parseInt(environment.getProperty("connection.pool.maxIdleTime")));
        dataSource.setMinPoolSize(Integer.parseInt(environment.getProperty("connection.pool.minPoolSize")));
        dataSource.setMaxPoolSize(Integer.parseInt(environment.getProperty("connection.pool.maxPoolSize")));

        return dataSource;
    }

    private Properties getHibernateProperties() {
        // set hibernate properties
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        props.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        return props;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){

        // create session factory
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        // set the properties
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan(environment.getProperty("hibernate.packagesToScan"));
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

        // setup transaction manager based on session factory
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }
}
