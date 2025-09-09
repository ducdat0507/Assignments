FROM mysql:8.0
ENV MYSQL_ROOT_PASSWORD=1234567
ENV MYSQL_DATABASE=Students
ENV MYSQL_USER=test
ENV MYSQL_PASSWORD=1234567
EXPOSE 4306

FROM mcr.microsoft.com/dotnet/aspnet:8.0 AS base
WORKDIR /app
EXPOSE 8080

FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build
WORKDIR /src
COPY ["./", "5/"]
RUN dotnet restore "5/5.csproj"
COPY . .
WORKDIR "/src/5"
RUN dotnet build "5.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "5.csproj" -c Release -o /app/publish /p:UseAppHost=false

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "5.dll"]