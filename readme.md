# Common GemFire Converters

Spring Converters are part of the Spring Framework's type conversion system, which allows you to convert between different types in a consistent and flexible manner. They are especially useful in web applications where you need to convert request parameters into Java objects and vice versa.

# Why would I need this for GemFire?

In various spring projects I have found the converters can hamper application startup as Spring iterates through the GemFire Region and inspecting the contents.   This could be extremely costly and a tremendous impact to application start up as GemFire Regions could hold terabytes of data or more.

The last time I have used these converters spring XML was my favorite.   So I have updated and thrown the `@Component` annotation on it so in theory adding a `@ComponentScan(basePackages = "dev.gemfire.spring.converters")` to your spring application should do the job.  

In the future I will add a demo application highlighting these converters in action.

You can learn more about spring converter over at the spring documentation:  https://docs.spring.io/spring-framework/reference/core/validation/convert.html

Just for fun I have included a ChatGPT generated reply to "**What are spring converters?**"

### Key Concepts of Spring Converters (ChatGPT generated)

1. **Converter Interface**: The core of the Spring conversion system is the `Converter<S, T>` interface, where `S` is the source type and `T` is the target type. Implementing this interface allows you to define custom conversion logic.

    ```java
    public interface Converter<S, T> {
        T convert(S source);
    }
    ```

2. **GenericConverter Interface**: For more complex conversion scenarios, Spring provides the `GenericConverter` interface, which allows you to specify multiple source and target types.

    ```java
    public interface GenericConverter {
        Set<ConvertiblePair> getConvertibleTypes();
        Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType);
    }
    ```

3. **ConversionService**: The `ConversionService` interface is a central registry where you can register your custom converters. It provides methods to convert between types and to check if a conversion is possible.

    ```java
    public interface ConversionService {
        boolean canConvert(Class<?> sourceType, Class<?> targetType);
        <T> T convert(Object source, Class<T> targetType);
    }
    ```

4. **Default ConversionService**: Spring provides a default implementation called `DefaultConversionService`, which comes with many built-in converters for common types (e.g., String to Integer).

    ```java
    ConversionService conversionService = new DefaultConversionService();
    ```

### Creating and Using Custom Converters

#### Step 1: Implement a Converter

Here's an example of a custom converter that converts a `String` to a custom `User` object:

```java
public class StringToUserConverter implements Converter<String, User> {
    @Override
    public User convert(String source) {
        String[] data = source.split(",");
        return new User(data[0], data[1]); // Assuming User has a constructor User(String id, String name)
    }
}
```

#### Step 2: Register the Converter with ConversionService

You can register your custom converter with the `ConversionService`:

```java
ConversionService conversionService = new DefaultConversionService();
((DefaultConversionService) conversionService).addConverter(new StringToUserConverter());

String userString = "123,John Doe";
User user = conversionService.convert(userString, User.class);
```

#### Step 3: Use Converters in Spring Applications

Spring MVC can automatically use the registered converters to convert request parameters to method arguments. You can configure the `ConversionService` in a Spring application context:

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToUserConverter());
    }
}
```

With this configuration, Spring MVC will use the `StringToUserConverter` to convert `String` parameters in HTTP requests to `User` objects in controller method parameters.

```java
@RestController
public class UserController {

    @GetMapping("/user")
    public User getUser(@RequestParam("user") User user) {
        return user;
    }
}
```

### Summary

- **Spring Converters** are used to convert between different types in a Spring application.
- **Custom Converters** can be created by implementing the `Converter` or `GenericConverter` interface.
- **ConversionService** is used to register and manage converters.
- **Spring MVC Integration** allows automatic conversion of request parameters to method arguments using registered converters.


